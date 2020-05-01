package the.school.learning.mapper;

import the.school.learning.entity.CommentReply;

public interface CommentReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentReply record);

    int insertSelective(CommentReply record);

    CommentReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentReply record);

    int updateByPrimaryKeyWithBLOBs(CommentReply record);

    int updateByPrimaryKey(CommentReply record);
}