package org.thisdote.innerjoinus.articlereply.article.command.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseUser {
    private Date userBirthday;
    private String userPhone;
    private String userEmail;
    private int userStudyGroupStatus;
    private Date userRegistDate;
    private Date userInfoUpdateDate;
    private int userGrade;
}
