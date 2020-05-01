package the.school.learning.mapper;

import org.apache.ibatis.annotations.Param;
import the.school.learning.common.vo.CommentReplyVo;
import the.school.learning.common.vo.CommentVo;
import the.school.learning.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    int selectCommentCount();

    List<CommentVo> selectCommentByPage(@Param("limit") int limit, @Param("offset") int offset);

    List<CommentReplyVo> selectCommentReplyByPage(@Param("limit") int limit, @Param("offset") int offset,
                                                  @Param("commentId") int commentId);

    int selectCommentReplyCount(int commentId);

}
