package org.thisdote.innerjoinus.user.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResponseUserFeignArticlesAndReplies {
    private Integer userCode;
    private String userId;
    private Date userBirthday;
    private String userPhone;
    private String userEmail;
    private Integer userStudyGroupStatus;
    private Date userRegistDate;
    private Date userInfoUpdateDate;
    private Integer userGrade;
    private Integer userResignStatus;
    private List<ResponseArticle> articleList;
    private List<ResponseReply> replyList;
}
