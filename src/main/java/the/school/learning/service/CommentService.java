package the.school.learning.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import the.school.learning.common.result.Page;
import the.school.learning.common.utils.DateUtils;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.CommentReplyVo;
import the.school.learning.common.vo.CommentVo;
import the.school.learning.common.vo.UserRoleVo;
import the.school.learning.entity.Comment;
import the.school.learning.entity.CommentReply;
import the.school.learning.entity.User;
import the.school.learning.mapper.CommentMapper;
import the.school.learning.mapper.CommentReplyMapper;
import the.school.learning.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentReplyMapper commentReplyMapper;
    @Resource
    private UserMapper userMapper;

    public Comment selectCommentByPrimaryKey(Integer commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    //添加留言
    public void insert(Comment comment) {
        UserRoleVo currentUser = UserUtils.getCurrentUser();
        comment.setUserId(currentUser.getId());
        comment.setStatus("1");
        comment.setCreateTime(DateUtils.now());
        this.commentMapper.insertSelective(comment);
    }

    //回复评论
    public void replyComment(CommentReply item) {
        UserRoleVo currentUser = UserUtils.getCurrentUser();
        item.setUserId(currentUser.getId());
        item.setReplyTime(DateUtils.now());
        this.commentReplyMapper.insertSelective(item);
    }

    //留言列表
    public Page<CommentVo> selectCommentByPage(Page<CommentVo> page) {
        int count = this.commentMapper.selectCommentCount();
        if (count == 0) {
            return Page.empty();
        }
        page.setTotalRecord(count);
        List<CommentVo> list = this.commentMapper.selectCommentByPage(page.limit(), page.offset());
        if (list != null && list.size() > 0) {
            for (CommentVo item : list) {
                if (item.getReplyCount() == null) {
                    item.setReplyCount(0);
                }
                if (StringUtils.isBlank(item.getLastReply())) {
                    item.setLastReply("");
                    item.setLastReplyTime("");
                }
            }
        }
        page.setList(list);
        return page;
    }

    public Page<CommentReplyVo> selectCommentReplyByPage(Page<CommentReplyVo> page, Integer commentId) {
        List<CommentReplyVo> retList = new ArrayList<>();
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        boolean firstPage = false;
        if (page.getCurrentPage() == 1) {//第一页是包含原帖
            firstPage = true;
            CommentReplyVo commentReplyVo = new CommentReplyVo();
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            commentReplyVo.setAuthor(user.getNickname());
            commentReplyVo.setContent(comment.getContent());
            commentReplyVo.setCreateTime(comment.getCreateTime());
            commentReplyVo.setFloor("1F");
            commentReplyVo.setSelf(true);

            if (comment.getUserId() == 1) {
                commentReplyVo.setAdmin(true);
            }

            retList.add(commentReplyVo);

            page.setPageSize(page.getPageSize() - 1);
        }


        int count = this.commentMapper.selectCommentReplyCount(commentId);
        page.setTotalRecord(count + 1);
        if (count > 0) {
            int offset = page.offset();
            if (!firstPage) {
                offset = offset -1;
            }
            List<CommentReplyVo> list = this.commentMapper.selectCommentReplyByPage(page.limit(), offset, commentId);
            if (list != null && list.size() > 0) {
                for (CommentReplyVo item : list) {
                    item.setFloor((item.getReplyId() + 1) + "F");
                    if (item.getReplyId() == 1) {
                        item.setAdmin(true);
                    }
                    if (item.getReplyId().equals(comment.getUserId())) {
                        item.setSelf(true);
                    }
                }
                retList.addAll(list);
            }
        }
        page.setList(retList);
        return page;
    }
}
