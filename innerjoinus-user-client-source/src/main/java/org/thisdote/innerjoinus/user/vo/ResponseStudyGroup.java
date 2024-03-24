package org.thisdote.innerjoinus.user.vo;

import lombok.Data;
import org.thisdote.innerjoinus.user.dto.StudyGroupMemberDTO;

import java.util.List;

@Data
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
