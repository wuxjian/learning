package the.school.learning.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import the.school.learning.common.utils.DateUtils;
import the.school.learning.common.utils.MD5;
import the.school.learning.common.vo.UserVo;
import the.school.learning.entity.Role;
import the.school.learning.entity.User;
import the.school.learning.mapper.RoleMapper;
import the.school.learning.mapper.UserMapper;

import javax.annotation.Resource;

@Service
public class SysService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    public UserVo login(String username, String password) {
        User user = this.userMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }
        if (!MD5.comparePassword(password, user.getPassword())) {
            return null;
        }
        //更新登录时间
        user.setLogTime(DateUtils.now());
        userMapper.updateByPrimaryKeySelective(user);

        Role role = roleMapper.selectRoleByUserId(user.getId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setRole(role);

        return userVo;
    }

}
