package org.thisdote.innerjoinus.articlereply.article.query.vo;

import lombok.Data;
import org.thisdote.innerjoinus.articlereply.article.command.vo.ResponseUser;

import java.util.Date;

@Data
public class ResponseAllArticle {
    private int articleId;
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
    private int articleQuestionCategory;
    private int userCode;
    private int studygroupId;
    private int studygroupCurrentMemberCount;
    private int studygroupPendingMemberCount;
    private int articleDeleteStatus;
    //    private List<ResponseUser> userList;
    private ResponseUser userList;
}