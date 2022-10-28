package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.config.ResponseResult;
import com.sangeng.constants.SysConstants;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.entity.Category;
import com.sangeng.domain.vo.CategoryVo;
import com.sangeng.mapper.CategoryMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult<?> getCategoryList() {
        //查询文章表 状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SysConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id, 并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
                //.map(article -> article.getCategoryId());
                //.map((Function<Article, Object>) Article::getCategoryId);
                //.map((Function<Article, Object>) article -> article.getCategoryId());
                /*.map(new Function<Article, Object>() {
                    @Override
                    public Object apply(Article article) {
                        return article.getCategoryId();
                    }
                })*/
        //查询分类表
        List<Category> categoryList = listByIds(categoryIds);
        //过滤 判断状态是否正常 category -> {}
        categoryList.stream().filter(category ->SysConstants.STATUS_NORMAL.equals(category.getStatus()));
        //封装成VO类型
        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);
        return ResponseResult.okResult(categoryVoList);
    }
}
