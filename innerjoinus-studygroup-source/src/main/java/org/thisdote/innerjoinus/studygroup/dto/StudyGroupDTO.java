package org.thisdote.innerjoinus.studygroup.dto;

import lombok.*;
import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StudyGroupDTO {

    private int studyGroupId;
    private int studyGroupType;
    private Date studyGroupCreatedDate;
    private int studyGroupMemberCount;
    private int studyGroupActivationStatus;
    private Date studyGroupStudyTime;
    private String studyGroupContent;
    private int studyGroupDeleteStatus;
    private List<StudyGroupMemberDTO> studyGroupMemberDTOList;

}
