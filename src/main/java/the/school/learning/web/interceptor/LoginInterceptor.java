package the.school.learning.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import the.school.learning.common.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute(Constant.SESSION_USER);
        if (user == null) {
            request.setAttribute("msg","无权限请先登录");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        }
        return true;
    }
}
