package org.thisdote.innerjoinus.articlereply.reply.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.articlereply.reply.query.aggregate.ReplyQueryEntity;

public interface ReplyQueryRepository extends JpaRepository<ReplyQueryEntity, Integer> {
}
