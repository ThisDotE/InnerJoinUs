package org.thisdote.innerjoinus.articlereply.article.query.repository;

import org.apache.ibatis.annotations.Mapper;
import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    List<ArticleDTO> selectAllArticle();

    List<ArticleDTO> selectAllQuestionArticle();

    List<ArticleDTO> selectArticleByUser(int userCode);

    List<ArticleDTO> selectStudyArticleInfo(int articleId, int studyCate);

    List<ArticleDTO> selectArticleByCriteria(Map<String, Object> criteria);

    List<ArticleDTO> currentDaySelect();

    List<ArticleDTO> selectAllStudyArticle();
}
