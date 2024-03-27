package org.thisdote.innerjoinus.articlereply.reportedreply.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleReplyDTO {
    private int reportReplyId;
    private int articleId;
    private int userCode;
    private Date reportDate;
    private String reportContent;
    private int reportReportedCount;
    private int reportType;
}
