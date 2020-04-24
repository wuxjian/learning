package the.school.learning.mapper;

import org.apache.ibatis.annotations.Param;
import the.school.learning.common.vo.ArticleUserVo;
import the.school.learning.entity.ArticleLearn;

import java.util.List;

public interface ArticleLearnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLearn record);

    int insertSelective(ArticleLearn record);

    ArticleLearn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleLearn record);

    int updateByPrimaryKey(ArticleLearn record);

    ArticleLearn selectByArticleIdAndUserId(@Param("articleId") Integer articleId, @Param("articleId") Integer userId);

    List<ArticleUserVo> selectArticlePage(Integer userId, @Param("limit") Integer limit,
                                          @Param("offset") Integer offset);

    int selectCount();
}
