package the.school.learning.service;

import org.springframework.stereotype.Service;
import the.school.learning.common.result.Page;
import the.school.learning.common.vo.UserVo;
import the.school.learning.entity.Article;
import the.school.learning.mapper.ArticleMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    public void insert(Article article) {
        this.articleMapper.insertSelective(article);
    }

    public Page<UserVo> selectByPage(Page<UserVo> page) {
        int count = this.articleMapper.selectCount();
        if (count == 0) {
            return Page.empty();
        }
        page.setTotalRecord(count);
        List<UserVo> list = this.articleMapper.selectByPage(page.limit(), page.offset());
        page.setList(list);
        return page;
    }

    public void toggleStatus(Integer id) {
        Article article = this.articleMapper.selectByPrimaryKey(id);
        if (article.getStatus().equals("1")) {
            article.setStatus("2");
        }else {
            article.setStatus("1");
        }
        this.articleMapper.updateByPrimaryKeySelective(article);
    }

    public Article detail(Integer id) {
        return this.articleMapper.selectByPrimaryKey(id);
    }
}
