package org.thisdote.innerjoinus.articlereply.reply.query.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReplyQueryEntity {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;

    @Column(name = "reply_report_status")
    private int replyReportStatus;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_create_date")
    private Date replyCreatedDate;

    @Column(name = "reply_last_update_date")
    private Date replyLastUpdateDate;

    @Column(name = "reply_like_count")
    private int replyLikeCount;

    @Column(name = "user_code")
    private int userCode;

    @Column(name = "article_id")
    private int articleId;

    @Column(name = "reply_delete_status")
    private int replyDeleteStatus;
}
