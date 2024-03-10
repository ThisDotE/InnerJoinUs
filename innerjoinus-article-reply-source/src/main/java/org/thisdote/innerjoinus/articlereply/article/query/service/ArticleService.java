package org.thisdote.innerjoinus.articlereply.article.query.service;

import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<ArticleDTO> selectAllArticle();
    List<ArticleDTO> selectArticleByUser(int userCode);
    List<ArticleDTO> selectAllQuestionArticle();
    List<ArticleDTO> selectStudyArticleInfo(int articleId, int studyCate);
    List<ArticleDTO> selectArticleByCriteria(Map<String, Object> criteria);

    ArticleDTO selectArticleByArticleId(int articleId);
}
