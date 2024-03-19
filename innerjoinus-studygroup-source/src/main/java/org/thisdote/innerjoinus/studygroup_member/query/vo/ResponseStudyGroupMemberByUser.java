package org.thisdote.innerjoinus.studygroup_member.query.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseStudyGroupMemberByUser {
    private int studyGroupId;
    private int studyGroupRole;     // 0 - 스터디원, 1 - 스터디장
    private Date studyGroupRegistDate;
}
