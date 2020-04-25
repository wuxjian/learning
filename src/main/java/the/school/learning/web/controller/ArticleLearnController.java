package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.utils.DateUtils;
import the.school.learning.common.vo.ArticleUserVo;
import the.school.learning.entity.Article;
import the.school.learning.entity.ArticleLearnItems;
import the.school.learning.service.ArticleLearnService;
import the.school.learning.service.ArticleService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/article/learn")
public class ArticleLearnController {
    @Resource
    private ArticleLearnService service;
    @Resource
    private ArticleService articleService;

    @PostMapping("/insert")
    @ResponseBody
    public Result insert(ArticleLearnItems param) {
        this.service.insertLearn(param);
        return Result.success();
    }

    @GetMapping("/list.html")
    public String list() {
        return "article-learn/article-learn-list";
    }



    @GetMapping("/learning.html")
    public String learning(Model model, Integer articleId) {
        Article article = this.articleService.detail(articleId);
        model.addAttribute("article", article);
        model.addAttribute("startTime", DateUtils.now());
        model.addAttribute("articleId", articleId);
        return "article-learn/article-learning";
    }

    @PostMapping("/list")
    @ResponseBody
    public Result selectByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                               @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<ArticleUserVo> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<ArticleUserVo> result = this.service.selectByPage(param);
        return Result.success(result);
    }

}
