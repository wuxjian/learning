package the.school.learning.mapper;

import org.apache.ibatis.annotations.Param;
import the.school.learning.common.vo.UserVo;
import the.school.learning.entity.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    List<UserVo> selectByPage(@Param("limit") Integer limit, @Param("offset") Integer offset);

    int selectCount();
}
