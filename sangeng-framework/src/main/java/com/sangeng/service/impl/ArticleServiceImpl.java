package com.sangeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sangeng.config.ResponseResult;
import com.sangeng.config.SYResponse;
import com.sangeng.constants.SysConstants;
import com.sangeng.domain.entity.Article;
import com.sangeng.domain.entity.Category;
import com.sangeng.domain.vo.ArticleDetailVo;
import com.sangeng.domain.vo.ArticleListVo;
import com.sangeng.domain.vo.HotArticleVo;
import com.sangeng.domain.vo.PageVo;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.service.ArticleService;
import com.sangeng.service.CategoryService;
import com.sangeng.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;


    /**
     * 查询热门文章 封装成ResponseResult返回
     */
    @Override
    public ResponseResult<?> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章 //逻辑删除
        queryWrapper.eq(Article::getStatus, SysConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        //queryWrapper.orderByAsc(Article::getViewCount);
        //按照升序排列
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        List<Article> articleList = page.getRecords();
        //bean拷贝 依靠字段的名字和类型
        /*List<HotArticleVo> articleVoList = new ArrayList<>();
        for (Article article : articleList) {
            HotArticleVo vo = new HotArticleVo();
            BeanUtils.copyProperties(article,vo);
            articleVoList.add(vo);
        }*/
        //bean拷贝 依靠字段的名字和类型 调用工具类
        List<HotArticleVo> articleVoList = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);

        return ResponseResult.okResult(articleVoList);
    }

    @Override
    public SYResponse<?> hotList() {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章 //逻辑删除
        lambdaQueryWrapper.eq(Article::getStatus, 0);
        //按照浏览量进行排序
        lambdaQueryWrapper.orderByAsc(Article::getViewCount);
        //按照升序排列

        //最多只查询10条
        Page<Article> page = new Page<>(1, 5);

        page(page, lambdaQueryWrapper);

        List<Article> records = page.getRecords();
        return new SYResponse<>(200, "成功", records);
    }

    @Override
    public ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //如果有 categoryId 就要查询时 要和传入时的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,
                Article::getCategoryId,
                categoryId);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,
                SysConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page, lambdaQueryWrapper);
        System.err.println(page);
        //查询categoryName

        List<Article> articleList = page.getRecords();
        articleList.stream()
                //获取分类id 查询分类信息 获取分类名称
                //把分类名称设置给article
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
                /*.map(article -> {
                    //获取分类id 查询分类信息 获取分类名称
                    //把分类名称设置给article
                    return article.setCategoryName(categoryService.getById(article.getCategoryId()).getName());
                });*/
                /*.map(article -> {
                    //获取分类id 查询分类信息 获取分类名称
                    Category category = categoryService.getById(article.getCategoryId());
                    //把分类名称设置给article
                    article.setCategoryName(category.getName());
                    return article;
                });*/
                /*.map(new Function<Article, Article>() {
                    @Override
                    public Article apply(Article article) {
                        //获取分类id 查询分类信息 获取分类名称
                        Category category = categoryService.getById(article.getCategoryId());
                        //把分类名称设置给article
                        article.setCategoryName(category.getName());
                        return article;
                    }
                });*/

        //articleId去查询articleName进行设置 通过for循环来实现
        /*for (Article article : articleList) {
            //根据文章表中的分类id查出分类表中的相关数据
            Category category = categoryService.getById(article.getCategoryId());
            //把分类表中的名字传到文章表中的分类名称
            article.setCategoryName(category.getName());
        }*/

        //封装查询结果
        List<ArticleListVo> articleListVoList = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVoList, page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    /**
     * 获取文章详情
     *
     * @param id 区分id
     */
    @Override
    public ResponseResult<?> getArticleDetail(Long id) {
        //根据id查询文章详情
        Article article = getById(id);
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        //Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        if (category != null) {
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}
