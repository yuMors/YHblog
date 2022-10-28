package com.sangeng.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sangeng.domain.entity.Article;
import com.sangeng.service.ArticleService;
import com.sangeng.config.ResponseResult;
import com.sangeng.config.SYResponse;
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
        List<Article> articleList = articleService.list();
        return articleList;
    }

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
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
