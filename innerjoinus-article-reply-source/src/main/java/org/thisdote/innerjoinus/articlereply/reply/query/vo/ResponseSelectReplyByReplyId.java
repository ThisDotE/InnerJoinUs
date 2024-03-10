package org.thisdote.innerjoinus.articlereply.reply.query.vo;

import lombok.Data;
import org.thisdote.innerjoinus.articlereply.article.command.vo.ResponseUser;

import java.util.Date;

@Data
public class ResponseSelectReplyByReplyId {
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
