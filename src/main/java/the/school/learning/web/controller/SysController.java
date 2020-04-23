package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.result.Result;
import the.school.learning.common.vo.UserRoleVo;
import the.school.learning.service.SysService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping
public class SysController {

    @Resource
    private SysService sysService;


    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpSession session) {
        UserRoleVo userRoleVo = sysService.login(username, password);
        if (Objects.nonNull(userRoleVo)) {
            session.setAttribute(Constant.SESSION_USER, userRoleVo);
            return Result.success();
        }
        return Result.error("用户名或密码错误");
    }


    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/register.html")
    public String register() {
        return "register";
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
