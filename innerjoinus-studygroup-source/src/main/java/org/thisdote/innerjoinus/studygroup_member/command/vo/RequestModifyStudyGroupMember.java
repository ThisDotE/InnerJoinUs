package org.thisdote.innerjoinus.studygroup_member.command.vo;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestModifyStudyGroupMember {

    private int studyGroupId;
    private int studyGroupRole;
    private Date studyGroupRegistDate;
    private int userCode;

}