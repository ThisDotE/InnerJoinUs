package org.thisdote.innerjoinus.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseStudygroupMember {
    private int studyGroupId;
    private int studyGroupRole;     // 0 - 스터디원, 1 - 스터디장
    private Date studyGroupRegistDate;
}
