package org.thisdote.innerjoinus.articlereply.article.command.service;

import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;

public interface CommandArticleService {
    String deleteArticle(ArticleDTO articleDTO);

    ArticleDTO modifyArticle(ArticleDTO modifyArticle);

    ArticleDTO registArticle(ArticleDTO articleDTO);

    ArticleDTO selectArticleUser(int articleId);
}
