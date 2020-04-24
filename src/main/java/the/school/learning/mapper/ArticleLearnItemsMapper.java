package the.school.learning.mapper;

import the.school.learning.entity.ArticleLearnItems;

public interface ArticleLearnItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleLearnItems record);

    int insertSelective(ArticleLearnItems record);

    ArticleLearnItems selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleLearnItems record);

    int updateByPrimaryKey(ArticleLearnItems record);
}