package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.entity.Answer;
import the.school.learning.entity.Examination;
import the.school.learning.service.ExaminationService;

import javax.annotation.Resource;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/examination")
public class ExaminationController {
    @Resource
    private ExaminationService service;

    @GetMapping("/list.html")
    public String list() {
        return "examination/examination-list";
    }

    @PostMapping("/insert")
    @ResponseBody
    public Result insert(Examination param) {
        this.service.insert(param);
        return Result.success();
    }
    @GetMapping("/add.html")
    public String add() {
        return "examination/examination-add";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result delete(Integer id) {
        service.delete(id);
        return Result.success();
    }



    @PostMapping("/list")
    @ResponseBody
    public Result selectByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                               @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<Examination> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<Examination> result = this.service.selectByPage(param);
        return Result.success(result);
    }


    @GetMapping("/answer/list.html")
    public String answerList() {
        return "examination/examination-answer-list";
    }
    @GetMapping("/answer.html")
    public String answerList(Model model, Integer examId) {
        Examination examination = service.selectByPrimaryKey(examId);
        model.addAttribute("exam", examination);
        examination.getExamCount();
        int[] arr = IntStream.rangeClosed(1, examination.getExamCount()).toArray();
        model.addAttribute("arr", arr);
        return "examination/examination-answer";
    }

    @PostMapping("/answer")
    @ResponseBody
    public Result answer(Answer answer){
        return  service.answer(answer);
    }
}
