package the.school.learning.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.utils.DateUtils;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.entity.Answer;
import the.school.learning.entity.Examination;
import the.school.learning.mapper.AnswerMapper;
import the.school.learning.mapper.ExaminationMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExaminationService {
    @Resource
    private ExaminationMapper mapper;
    @Resource
    private AnswerMapper answerMapper;

    public Examination selectByPrimaryKey(Integer examId) {
        return mapper.selectByPrimaryKey(examId);
    }

    //创建试题
    public void insert(Examination examination) {
        examination.setExamScore((short) (examination.getExamPerScore() * examination.getExamCount()));
        examination.setCreateOn(DateUtils.now());
        examination.setStatus(Constant.ENABLE);
        mapper.insertSelective(examination);
    }

    //删除试题
    public void delete(Integer examId) {
        Examination examination = new Examination();
        examination.setId(examId);
        examination.setStatus(Constant.DISABLE);
        mapper.updateByPrimaryKeySelective(examination);
    }

    //答题
    public Result answer(Answer answer) {
        Integer examId = answer.getExamId();
        Examination examination = mapper.selectByPrimaryKey(examId);
        //标准答案
        String[] standAnswers = examination.getAnswer().split(",");
        String[] yourAnswers = answer.getYourAnswer().split(",");

        //判断答题是不是有误
        if (standAnswers.length != yourAnswers.length) {
            return Result.error("无效答题");
        }
        //正确答案的数量
        int correctAnswerCount = 0;
        for (int i = 0; i < standAnswers.length; i++) {
            if (StringUtils.equalsAnyIgnoreCase(standAnswers[i], yourAnswers[i])) {
                correctAnswerCount++;
            }
        }
        //计算得分
        int examScore = correctAnswerCount * examination.getExamPerScore();
        answer.setScore(examScore);
        answer.setUserId(UserUtils.getCurrentUser().getId());
        answer.setCreateTime(DateUtils.now());
        answerMapper.insertSelective(answer);
        return Result.success(examScore);
    }

    public Page<Examination> selectByPage(Page<Examination> page) {
        int count = this.mapper.selectCount();
        if (count == 0) {
            return Page.empty();
        }
        page.setTotalRecord(count);
        List<Examination> list = this.mapper.selectByPage(page.limit(), page.offset());
        page.setList(list);
        return page;
    }
}
