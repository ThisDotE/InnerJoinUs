package org.thisdote.innerjoinus.user.dto;

import lombok.Data;
import org.thisdote.innerjoinus.user.vo.ResponseArticle;
import org.thisdote.innerjoinus.user.vo.ResponseReply;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private int userCode;
    private String userId;
    private String userPassword;
    private Date userBirthday;
    private String userPhone;
    private String userEmail;
    private int userStudyGroupStatus;
    private Date userRegistDate;
    private Date userInfoUpdateDate;
    private int userGrade;
    private int userResignStatus;       // 0 - 회원, 1 - 탈퇴 회원
    private StudyGroupMemberDTO studyGroupMemberDTO;
    private List<ResponseArticle> articleList;
    private List<ResponseReply> replyList;
}
