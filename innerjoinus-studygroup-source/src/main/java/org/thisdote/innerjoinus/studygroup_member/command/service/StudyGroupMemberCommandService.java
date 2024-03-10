package org.thisdote.innerjoinus.studygroup_member.command.service;

import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;

public interface StudyGroupMemberCommandService {


    StudyGroupMemberDTO registStudyGroupMember(StudyGroupMemberDTO studyGroupMemberDTO);

    void modifyStudyGroupMemberRole(StudyGroupMemberDTO studyGroupMemberDTO);

    String deleteStudyGroupMemberId(int studyGroupMemberId);

    StudyGroupMemberDTO selectStudyGroupMemberUser(int studyGroupMemberId);
}
