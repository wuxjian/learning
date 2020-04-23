package the.school.learning.entity;

import java.io.Serializable;

/**
 * article
 * @author 
 */
public class Article implements Serializable {
    private Integer id;

    /**
     * 学科
     */
    private String subject;

    /**
     * 1草稿状态2发布状态 只有发布状态普通用户才能看到
     */
    private String status;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}