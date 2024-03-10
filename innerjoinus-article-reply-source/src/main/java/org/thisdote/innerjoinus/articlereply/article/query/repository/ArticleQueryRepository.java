package org.thisdote.innerjoinus.articlereply.article.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.articlereply.article.query.aggregate.ArticleQueryEntity;

public interface ArticleQueryRepository extends JpaRepository<ArticleQueryEntity, Integer> {
}
