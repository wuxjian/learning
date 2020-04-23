package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.vo.UserVo;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute(Constant.SESSION_USER);
        model.addAttribute("user", userVo);
        return "user/profile";
    }
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute(Constant.SESSION_USER);
        model.addAttribute("user", userVo);
        return "user/list";
    }

}
