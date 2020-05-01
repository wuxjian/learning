package the.school.learning.entity;

import java.io.Serializable;

/**
 * comment
 * @author 
 */
public class Comment implements Serializable {
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 主题
     */
    private String subject;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 状态1正常2关闭 关闭下其他用户看不到
     */
    private String status;

    /**
     * 创建时间
     */
    private String createTime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}