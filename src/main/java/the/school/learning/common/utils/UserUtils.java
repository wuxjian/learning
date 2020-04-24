package the.school.learning.common.utils;

import the.school.learning.common.constant.Constant;
import the.school.learning.common.vo.UserRoleVo;

import javax.servlet.http.HttpSession;

public class UserUtils {

    public static UserRoleVo getCurrentUser() {
        HttpSession session = HttpUtils.request().getSession();
        return (UserRoleVo) session.getAttribute(Constant.SESSION_USER);
    }
}
