package org.thisdote.innerjoinus.studygroup_member.command.vo;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ResponseStudyGroupMemberUser {
    private int studyGroupMemberId;
    private int studyGroupId;
    private int studyGroupRole;
    private Date studyGroupRegistDate;
    private int userCode;
    private ResponseUser userList;
}
