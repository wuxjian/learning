package the.school.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.result.Result;
import the.school.learning.common.vo.UserVo;
import the.school.learning.service.SysService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping
public class SysController {

    @Resource
    private SysService sysService;


    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password,  HttpSession session) {
        UserVo userVo = sysService.login(username, password);
        if (Objects.nonNull(userVo)){
            session.setAttribute(Constant.SESSION_USER, userVo);
            return Result.success();
        }
        return Result.error("用户名或密码错误");
    }

}
