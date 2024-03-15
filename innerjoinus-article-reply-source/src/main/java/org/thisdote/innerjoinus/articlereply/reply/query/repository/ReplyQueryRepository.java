package org.thisdote.innerjoinus.articlereply.reply.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.articlereply.reply.query.aggregate.ReplyQueryEntity;

import java.util.List;

public interface ReplyQueryRepository extends JpaRepository<ReplyQueryEntity, Integer> {
    List<ReplyQueryEntity> findByArticleId(Integer articleId);
}
