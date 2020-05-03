package the.school.learning.entity;

import java.io.Serializable;

/**
 * examination
 * @author 
 */
public class Examination implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 测试题名称
     */
    private String examName;

    /**
     * 试题数量,最多五十道
     */
    private Short examCount;

    /**
     * 每题多少分
     */
    private Short examPerScore;

    /**
     * 总分
     */
    private Short examScore;

    /**
     * 答案,用,分隔
     */
    private String answer;

    /**
     * 状态1正常0删除
     */
    private String status;

    /**
     * 创建时间
     */
    private String createOn;

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

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Short getExamCount() {
        return examCount;
    }

    public void setExamCount(Short examCount) {
        this.examCount = examCount;
    }

    public Short getExamPerScore() {
        return examPerScore;
    }

    public void setExamPerScore(Short examPerScore) {
        this.examPerScore = examPerScore;
    }

    public Short getExamScore() {
        return examScore;
    }

    public void setExamScore(Short examScore) {
        this.examScore = examScore;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}