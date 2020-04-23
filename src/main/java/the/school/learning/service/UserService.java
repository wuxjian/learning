package the.school.learning.service;

import org.springframework.stereotype.Service;
import the.school.learning.common.result.Page;
import the.school.learning.common.vo.UserVo;
import the.school.learning.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

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
}
