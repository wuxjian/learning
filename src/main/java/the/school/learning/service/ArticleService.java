package the.school.learning.service;

import org.springframework.stereotype.Service;
import the.school.learning.entity.Article;
import the.school.learning.mapper.ArticleMapper;

import javax.annotation.Resource;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    public void insert(Article article) {
        this.articleMapper.insertSelective(article);
    }

}
