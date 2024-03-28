package org.thisdote.innerjoinus.articlereply.reportedarticle.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.articlereply.reportedarticle.command.aggregate.CommandReportedArticleEntity;

public interface CommandReportedArticleRepository extends JpaRepository<CommandReportedArticleEntity, Integer> {
}
