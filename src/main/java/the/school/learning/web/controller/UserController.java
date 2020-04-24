package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.UserRoleVo;
import the.school.learning.common.vo.UserVo;
import the.school.learning.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/profile.html")
    public String profile(Model model) {
        model.addAttribute("user", UserUtils.getCurrentUser());
        return "user/user-profile";
    }

    @GetMapping("/list.html")
    public String list() {
        return "user/user-list";
    }

    @PostMapping("/list")
    @ResponseBody
    public Result selectByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                                            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<UserVo> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<UserVo> result = this.userService.selectByPage(param);
        return Result.success(result);
    }
}
