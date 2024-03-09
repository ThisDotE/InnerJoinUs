package org.thisdote.innerjoinus.articlereply.article.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.articlereply.article.command.aggregate.ArticleEntity;
import org.thisdote.innerjoinus.articlereply.article.dto.ArticleDTO;

public interface CommandArticleRepository extends JpaRepository<ArticleEntity, Integer> {
}
