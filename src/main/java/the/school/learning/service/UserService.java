package the.school.learning.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.ProfileVo;
import the.school.learning.common.vo.UserRoleVo;
import the.school.learning.common.vo.UserVo;
import the.school.learning.entity.Role;
import the.school.learning.entity.User;
import the.school.learning.mapper.RoleMapper;
import the.school.learning.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    public UserRoleVo selectByPrimaryKey(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Role role = roleMapper.selectRoleByUserId(user.getId());
        UserRoleVo userRoleVo = new UserRoleVo();
        BeanUtils.copyProperties(user, userRoleVo);
        userRoleVo.setRole(role);
        return userRoleVo;
    }

    public Page<UserVo> selectByPage(Page<UserVo> page) {
        int count = this.userMapper.selectCount();
        if (count == 0) {
            return Page.empty();
        }
        page.setTotalRecord(count);
        List<UserVo> list = this.userMapper.selectByPage(page.limit(), page.offset());
        page.setList(list);
        return page;
    }

    public ProfileVo profileStatistic(Integer userId) {
        if (userId == null) {
            userId = UserUtils.getCurrentUser().getId();
        }
        ProfileVo profileVo = new ProfileVo();
        List<Integer> list = userMapper.profileStatistic(userId);
        profileVo.setLearnCount(list.get(0) == null ? 0 : list.get(0));
        profileVo.setLearnTimes(list.get(1) == null ? 0 : list.get(1));
        profileVo.setCommentCount(list.get(2) == null ? 0 : list.get(2));
        profileVo.setCommentReplyCount(list.get(3) == null ? 0 : list.get(3));
        return profileVo;
    }

    public Result toggleStatus(Integer userId) {
        UserRoleVo user = selectByPrimaryKey(userId);
        if (user.getRole().getRole().equals("1")) {
            return Result.error("对管理员无效");
        }
        String enabled = user.getEnabled();
        String opposite = StringUtils.equals(enabled, Constant.ENABLE) ?  Constant.DISABLE : Constant.ENABLE;
        User u = new User();
        u.setId(userId);
        u.setEnabled(opposite);
        userMapper.updateByPrimaryKeySelective(u);
        return Result.success();
    }
}
