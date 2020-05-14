package the.school.learning.mapper;

import org.apache.ibatis.annotations.Param;
import the.school.learning.common.vo.UserVo;
import the.school.learning.entity.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int selectCount();

    List<UserVo> selectByPage(@Param("limit") int limit, @Param("offset") int offset);
}
