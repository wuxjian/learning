package the.school.learning.mapper;

import org.apache.ibatis.annotations.Param;
import the.school.learning.entity.Examination;

import java.util.List;

public interface ExaminationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Examination record);

    int insertSelective(Examination record);

    Examination selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Examination record);

    int updateByPrimaryKeyWithBLOBs(Examination record);

    int updateByPrimaryKey(Examination record);

    int selectCount();

    List<Examination> selectByPage(@Param("limit") int limit, @Param("offset") int offset);
}
