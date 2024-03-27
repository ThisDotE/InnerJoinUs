package org.thisdote.innerjoinus.studygroup_member.query.repository;

import org.apache.ibatis.annotations.Mapper;
import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;

import java.util.List;

@Mapper
public interface StudyGroupMemberMapper {

    List<StudyGroupMemberDTO> selectAllStudyGroupMember();

    List<StudyGroupMemberDTO> selectAllStudyGroupLeader();

    List<StudyGroupMemberDTO> selectAllStudyGroupFollower();

    List<StudyGroupMemberDTO> selectStudyGroupMemberById();
}