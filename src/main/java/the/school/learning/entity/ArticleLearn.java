package the.school.learning.entity;

import java.io.Serializable;

/**
 * article_learn
 * @author 
 */
public class ArticleLearn implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 文章的id
     */
    private Integer articleId;

    /**
     * 上次学习时间
     */
    private String lastLearnTime;

    /**
     * 文章学习次数
     */
    private Integer learnCount;

    /**
     * 学习次数
     */
    private Integer learnTimes;

    /**
     * 1 正常 0 删除
     */
    private String enabled;

    /**
     * 用户id
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getLastLearnTime() {
        return lastLearnTime;
    }

    public void setLastLearnTime(String lastLearnTime) {
        this.lastLearnTime = lastLearnTime;
    }

    public Integer getLearnCount() {
        return learnCount;
    }

    public void setLearnCount(Integer learnCount) {
        this.learnCount = learnCount;
    }

    public Integer getLearnTimes() {
        return learnTimes;
    }

    public void setLearnTimes(Integer learnTimes) {
        this.learnTimes = learnTimes;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}