package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.vo.ExaminationRecordVo;
import the.school.learning.service.ExaminationRecordService;

import javax.annotation.Resource;
import java.util.List;

//学习记录
@Controller
@RequestMapping("/examination/record")
public class ExaminationRecordController {
    @Resource
    private ExaminationRecordService service;


    @GetMapping("/list.html")
    public String list() {
        return "examination/examination-record-list";
    }




    @PostMapping("/list")
    @ResponseBody
    public Result selectByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                               @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<ExaminationRecordVo> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<ExaminationRecordVo> result = this.service.selectByPage(param);
        return Result.success(result);
    }

    @PostMapping("/chart")
    @ResponseBody
    public Result chart(@RequestParam(name = "recentCounts", defaultValue = "7") Integer recentCounts){
        List<ExaminationRecordVo> result = this.service.chart(recentCounts);
        return Result.success(result);
    }

}
