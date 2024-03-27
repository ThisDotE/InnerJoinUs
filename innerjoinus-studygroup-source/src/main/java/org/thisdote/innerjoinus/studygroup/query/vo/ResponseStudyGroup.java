package org.thisdote.innerjoinus.studygroup.query.vo;

import lombok.*;
import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ResponseStudyGroup {

    private int studyGroupId;
    private int studygroupType;
    private String studygroupCreateDate;
    private int studygroupMemberCount;
    private int studygroupActivationStatus;
    private String studygroupStudyTime;
    private String studygroupContent;
    private int studygroupDeleteStatus;
    private List<StudyGroupMemberDTO> studyGroupMemberDTOList;
}
