package the.school.learning.mapper;

import org.apache.ibatis.annotations.Param;
import the.school.learning.common.vo.ExaminationRecordVo;
import the.school.learning.entity.Answer;

import java.util.List;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);

    int selectCount(Integer userId);

    List<ExaminationRecordVo> selectExamRecordPage(@Param("userId") Integer userId, @Param("limit") int limit, @Param("offset") int offset);
}
