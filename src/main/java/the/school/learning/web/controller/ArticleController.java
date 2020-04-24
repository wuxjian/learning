package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.vo.UserVo;
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
        return "article/article-list";
    }

    @PostMapping("/insert")
    @ResponseBody
    public Result insert(Article article) {
        article.setStatus("1");
        this.service.insert(article);
        return Result.success();
    }
    @GetMapping("/add.html")
    public String add() {
        return "article/article-add";
    }


    @PostMapping("/list")
    @ResponseBody
    public Result selectByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                               @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<UserVo> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<UserVo> result = this.service.selectByPage(param);
        return Result.success(result);
    }

    @PostMapping("/toggleStatus")
    @ResponseBody
    public void toggleStatus(Integer id) {
        this.service.toggleStatus(id);
    }

}
