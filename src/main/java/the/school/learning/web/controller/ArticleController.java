package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import the.school.learning.common.result.Result;
import the.school.learning.entity.Article;
import the.school.learning.service.ArticleService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService service;

    @GetMapping("/list.html")
    public String list() {
        return "article/list";
    }

    @PostMapping("/insert")
    public Result insert(Article article) {
        this.service.insert(article);
        return Result.success();
    }
}
