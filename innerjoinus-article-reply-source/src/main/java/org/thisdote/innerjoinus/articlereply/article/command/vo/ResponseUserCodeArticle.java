package org.thisdote.innerjoinus.articlereply.article.command.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResponseUserCodeArticle {
    private String articleTitle;
    private String articleContent;
    private int articleCategory;
    private Date articleCreateDate;
    private Date articleLastUpdateDate;
    private int articleViewCount;
    private int articleLikeCount;
    private int articleReplyCount;
    private int articleReportStatus;
    private int studygroupMemberMaxCount;
    private Date studygroupRecruitmentDeadline;
    private String articleQuestionCategory;
    private int userCode;
    private int studygroupId;
    private int studygroupCurrentMemberCount;
    private int studygroupPendingMemberCount;
    private int articleDeleteStatus;
    private List<ResponseUserCode> userCodes;
}
