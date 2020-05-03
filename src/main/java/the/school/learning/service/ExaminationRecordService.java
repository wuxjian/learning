package the.school.learning.service;

import org.springframework.stereotype.Service;
import the.school.learning.common.result.Page;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.ExaminationRecordVo;
import the.school.learning.common.vo.UserRoleVo;
import the.school.learning.mapper.AnswerMapper;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class ExaminationRecordService {
    @Resource
    private AnswerMapper mapper;


    public Page<ExaminationRecordVo> selectByPage(Page<ExaminationRecordVo> page) {
        UserRoleVo user = UserUtils.getCurrentUser();
        int count = this.mapper.selectCount(user.getId());
        if (count == 0) {
            return Page.empty();
        }
        page.setTotalRecord(count);
        List<ExaminationRecordVo> list = this.mapper.selectExamRecordPage(user.getId(), page.limit(), page.offset());
        page.setList(list);
        return page;
    }


    public List<ExaminationRecordVo> chart(int recentCounts) {
        UserRoleVo currentUser = UserUtils.getCurrentUser();
        List<ExaminationRecordVo> list = this.mapper.selectExamRecordPage(currentUser.getId(),
                recentCounts, 0);
        Collections.reverse(list); // 倒序排列
        return list;
    }
}
