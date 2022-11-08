package com.sangeng.runner;

import com.sangeng.domain.entity.Article;
import com.sangeng.mapper.ArticleMapper;
import com.sangeng.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息 id viewCount
        //放到Map中 Map<key, value> 刚好 一个放id 一个放viewCount
        List<Article> articles = articleMapper.selectList(null);
        /*articles.stream()
                .collect(Collectors.toMap(new Function<Article, Long>() {
                    @Override
                    public Long apply(Article article) {
                        return article.getId();
                    }
                }, new Function<Article, Integer>() {
                    @Override
                    public Integer apply(Article article) {
                        return article.getViewCount().intValue();
                    }
                }));*/
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article1 -> article1.getId().toString(),
                        article -> article.getViewCount().intValue()));
        //存储到redis中 SystemConstants.ARTICLE_VIEWCOUNT
        redisCache.setCacheMap("article:viewCount",viewCountMap);

    }
}
