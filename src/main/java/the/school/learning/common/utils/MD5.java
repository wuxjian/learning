package the.school.learning.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import the.school.learning.common.constant.Constant;

public class MD5 {
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    //md5 密码（加盐）
    public static String md5PasswordWithSalt(String password) {
        return md5(Constant.SALT + password);
    }

    //比较密码
    public static boolean comparePassword(String originalPassword, String md5password) {
        return StringUtils.equals(md5PasswordWithSalt(originalPassword), md5password);
    }

    public static void main(String[] args) {
        String admin = md5PasswordWithSalt("admin");
        System.out.println(admin);
        String user = md5PasswordWithSalt("user");
        System.out.println(user);
    }
}
