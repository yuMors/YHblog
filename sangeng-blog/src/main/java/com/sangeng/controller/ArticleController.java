package com.sangeng.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sangeng.config.ResponseResult;
import com.sangeng.config.SYResponse;
import com.sangeng.domain.entity.Article;
import com.sangeng.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> test() {
        /*List<Article> articleList = articleService.list();
        return articleList;*/
        return articleService.list();
    }

    @GetMapping("/hotArticleList")
    public ResponseResult<?> hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        /*ResponseResult<?> result = articleService.hotArticleList();
        return result;*/
        return articleService.hotArticleList();
    }

    @GetMapping("/hotList")
    public SYResponse<?> hotArticleList02() {
        //查询热门文章 封装成ResponseResult返回
        //ResponseResult result = articleService.hotArticleList();
        //SYResponse<?> syResponse = articleService.hotList();
        return articleService.hotList();
    }

    @GetMapping("/articleList")
    public ResponseResult<?> articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    /**
     * 更新浏览量
     * 更新 修改 用put
     * 从零添加用post
     */
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult<?> updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }

    /**
     * 获取文章详情
     * @param id restful风格路径参数 需要加@PathVariable("id")
     * 上面的为query风格的路径参数
     */
    @GetMapping("/{id}")
    public ResponseResult<?> getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseResult<?> deleteOne(@PathVariable("id") Long id){
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,id);
        articleService.remove(queryWrapper);
        return ResponseResult.okResult("ok");
    }

}
