package org.thisdote.innerjoinus.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseReply {
    private int replyId;                // pk..
    private int replyReportStatus;
    private String replyContent;
    private Date replyCreatedDate;
    private Date replyLastUpdateDate;
    private int replyLikeCount;
    private int userCode;               // fk..
    private int articleId;              // fk..
    private int replyDeleteStatus;
}
