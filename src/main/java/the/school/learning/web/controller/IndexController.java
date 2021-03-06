package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.UserRoleVo;

import javax.servlet.http.HttpSession;

@RequestMapping
@Controller
public class IndexController {

    //首页 /index.html 或 空
    @GetMapping({"/index.html", ""})
    public String index(Model model) {
        model.addAttribute("user", UserUtils.getCurrentUser());
        return "index";
    }



}
