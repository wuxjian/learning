package the.school.learning.entity;

import java.io.Serializable;

/**
 * comment_reply
 * @author 
 */
public class CommentReply implements Serializable {
    private Integer id;

    /**
     * comment的id
     */
    private Integer commentId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 回复时间
     */
    private String replyTime;

    /**
     * 内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}