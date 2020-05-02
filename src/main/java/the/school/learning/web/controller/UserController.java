package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.ProfileVo;
import the.school.learning.common.vo.UserRoleVo;
import the.school.learning.common.vo.UserVo;
import the.school.learning.entity.User;
import the.school.learning.service.UserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/profile.html")
    public String profile(Model model, @RequestParam(value = "userId", required = false) Integer userId) {
        UserRoleVo userRoleVo;
        if (userId == null) {
            userRoleVo = UserUtils.getCurrentUser();
        }else {
            userRoleVo = userService.selectByPrimaryKey(userId);
        }
        model.addAttribute("userId", userId);
        model.addAttribute("user", userRoleVo);
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

    @GetMapping("/profileStatistic")
    @ResponseBody
    public Result profileStatistic(@RequestParam(value = "userId", required = false) Integer userId) {
        ProfileVo profileVo = this.userService.profileStatistic(userId);
        return Result.success(profileVo);
    }

    @PostMapping("/toggleStatus")
    @ResponseBody
    public Result toggleStatus(Integer userId) {
        return userService.toggleStatus(userId);
    }


    @PostMapping("/register")
    @ResponseBody
    public Result register(User user) {
        return userService.register(user);
    }
}
