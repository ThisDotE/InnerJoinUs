package org.thisdote.innerjoinus.articlereply.reportedarticle.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReportedArticle {
    private int reportArticleId;
    private int articleId;
    private int userCode;
    private Date reportDate;
    private String reportContent;
    private int reportReportedCount;
    private int reportType;
}
