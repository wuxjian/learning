package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.vo.ArticleLearnRecordVo;
import the.school.learning.service.ArticleLearnService;

import javax.annotation.Resource;
import java.util.List;

//学习记录
@Controller
@RequestMapping("/article/record")
public class ArticleRecordController {
    @Resource
    private ArticleLearnService service;


    @GetMapping("/list.html")
    public String list() {
        return "article-record/article-record-list";
    }




    @PostMapping("/list")
    @ResponseBody
    public Result selectByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                               @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<ArticleLearnRecordVo> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<ArticleLearnRecordVo> result = this.service.selectArticleRecordPage(param);
        return Result.success(result);
    }

    @PostMapping("/chart")
    @ResponseBody
    public Result chart(@RequestParam(name = "recentCounts", defaultValue = "7") Integer recentCounts){
        List<ArticleLearnRecordVo> result = this.service.chart(recentCounts);
        return Result.success(result);
    }

}
