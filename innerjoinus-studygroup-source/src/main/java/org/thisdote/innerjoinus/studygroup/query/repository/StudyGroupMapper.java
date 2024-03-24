package org.thisdote.innerjoinus.studygroup.query.repository;

import org.apache.ibatis.annotations.Mapper;
import org.thisdote.innerjoinus.studygroup.dto.StudyGroupDTO;

import java.util.List;

@Mapper
public interface StudyGroupMapper {

    List<StudyGroupDTO> selectAllStudyGroup();

    List<StudyGroupDTO> selectAllEnglishStudyGroup();

    List<StudyGroupDTO> selectAllLicenseStudyGroup();

    List<StudyGroupDTO> selectStudyGroupByStatus(int activationStatus);

    List<StudyGroupDTO> selectStudyGroupsByType(int studyGroupType);

    List<StudyGroupDTO> selectAllStudyGroupByUser(Integer userCode);
}
