package org.thisdote.innerjoinus.articlereply.reportedarticle.command.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "reported_article")
public class CommandReportedArticleEntity {
    @Id
    @Column
    private int reportArticleId;

    @Column
    private int articleId;

    @Column
    private int userCode;

    @Column
    private Date reportDate;

    @Column
    private String reportContent;

    @Column
    private int reportReportedCount;

    @Column
    private int reportType;

}
