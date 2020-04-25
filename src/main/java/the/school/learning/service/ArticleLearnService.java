package the.school.learning.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.school.learning.common.constant.Constant;
import the.school.learning.common.result.Page;
import the.school.learning.common.utils.DateUtils;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.ArticleLearnRecordVo;
import the.school.learning.common.vo.ArticleUserVo;
import the.school.learning.common.vo.UserRoleVo;
import the.school.learning.entity.ArticleLearn;
import the.school.learning.entity.ArticleLearnItems;
import the.school.learning.mapper.ArticleLearnItemsMapper;
import the.school.learning.mapper.ArticleLearnMapper;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class ArticleLearnService {

    @Resource
    private ArticleLearnMapper articleLearnMapper;
    @Resource
    private ArticleLearnItemsMapper articleLearnItemsMapper;

    @Transactional
    public void insertLearn(ArticleLearnItems param) {
        String now = DateUtils.now();
        param.setEndTime(now);
        UserRoleVo currentUser = UserUtils.getCurrentUser();
        //计算时长
        long intervalSecond = DateUtils.calTimeInterval(DateUtils.parseDate(param.getStartTime()), new Date());
        int intervalMinute = (int) (intervalSecond / 60);
        if ((intervalSecond % 60) != 0) {
            intervalMinute += 1;
        }
        param.setTimes(intervalMinute);
        param.setUserId(currentUser.getId());

        ArticleLearn articleLearn = articleLearnMapper.selectByArticleIdAndUserId(param.getArticleId(), currentUser.getId());
        //第一次学习
        boolean firstLearn = false;
        if (articleLearn == null) {
            articleLearn = new ArticleLearn();
            articleLearn.setUserId(currentUser.getId());
            articleLearn.setArticleId(param.getArticleId());
            articleLearn.setEnabled(Constant.ENABLE);
            articleLearn.setLearnCount(0);
            articleLearn.setLearnTimes(0);
            firstLearn = true;
        }

        articleLearn.setLastLearnTime(now);
        articleLearn.setLearnCount(articleLearn.getLearnCount() + 1);
        articleLearn.setLearnTimes(articleLearn.getLearnTimes() + intervalMinute);
        articleLearnItemsMapper.insertSelective(param);
        if (firstLearn) {
            articleLearnMapper.insertSelective(articleLearn);
        } else {
            articleLearnMapper.updateByPrimaryKeySelective(articleLearn);
        }
    }


    public Page<ArticleUserVo> selectByPage(Page<ArticleUserVo> page) {
        int count = this.articleLearnMapper.selectCount();
        if (count == 0) {
            return Page.empty();
        }
        page.setTotalRecord(count);
        UserRoleVo currentUser = UserUtils.getCurrentUser();
        List<ArticleUserVo> list = this.articleLearnMapper.selectArticlePage(currentUser.getId(), page.limit(), page.offset());
        page.setList(list);
        return page;
    }

    public Page<ArticleLearnRecordVo> selectArticleRecordPage(Page<ArticleLearnRecordVo> page) {
        UserRoleVo currentUser = UserUtils.getCurrentUser();
        int count = this.articleLearnMapper.selectRecordCount(currentUser.getId());
        if (count == 0) {
            return Page.empty();
        }
        page.setTotalRecord(count);

        List<ArticleLearnRecordVo> list = this.articleLearnMapper.selectArticleRecordPage(currentUser.getId(),
                page.limit(), page.offset());
        page.setList(list);
        return page;
    }

    public List<ArticleLearnRecordVo> chart(int recentCounts) {
        UserRoleVo currentUser = UserUtils.getCurrentUser();
        List<ArticleLearnRecordVo> list = this.articleLearnMapper.selectArticleRecordPage(currentUser.getId(),
                recentCounts, 0);
        Collections.reverse(list); // 倒序排列
        return list;
    }

}
