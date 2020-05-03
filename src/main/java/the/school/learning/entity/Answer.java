package the.school.learning.entity;

import java.io.Serializable;

/**
 * answer
 * @author 
 */
public class Answer implements Serializable {
    private Integer id;

    /**
     * 试题id
     */
    private Integer examId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 你的回答,多个答案用,分隔
     */
    private String yourAnswer;

    /**
     * 得分,自动计算出来的
     */
    private Integer score;

    /**
     * 创建时间
     */
    private String createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(String yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}