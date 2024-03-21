package org.thisdote.innerjoinus.articlereply.reportedreply.command.aggregate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "reported_article")
public class CommandReportedReplyEntity {
    @Id
    @Column
    private int reportReplyId;

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
