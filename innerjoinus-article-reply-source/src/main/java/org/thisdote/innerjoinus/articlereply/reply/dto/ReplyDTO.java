package org.thisdote.innerjoinus.articlereply.reply.dto;

import lombok.Data;
import org.thisdote.innerjoinus.articlereply.article.command.vo.ResponseUser;

import java.util.Date;

@Data
public class ReplyDTO {

    private int replyId;                // pk..
    private int replyReportStatus;
    private String replyContent;
    private Date replyCreatedDate;
    private Date replyLastUpdateDate;
    private int replyLikeCount;
    private int userCode;               // fk..
    private int articleId;              // fk..
    private int replyDeleteStatus;
    private ResponseUser responseUser;
}