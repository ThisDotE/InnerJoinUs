package org.thisdote.innerjoinus.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseUser {
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
    private String userLoginCode;
    private String userRole;
}
