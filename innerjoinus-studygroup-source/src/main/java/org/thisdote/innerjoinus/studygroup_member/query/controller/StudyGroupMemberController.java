package org.thisdote.innerjoinus.studygroup_member.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thisdote.innerjoinus.studygroup_member.query.service.StudyGroupMemberService;
import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;

import java.util.List;

@RestController
public class StudyGroupMemberController {

    private final StudyGroupMemberService studyGroupMemberService;

    @Autowired
    public StudyGroupMemberController(StudyGroupMemberService studyGroupMemberService) {
        this.studyGroupMemberService = studyGroupMemberService;
    }

    // 스터디그룹에 가입한 전체 멤버(스터디장, 스터디원) List에 담아서 조회하기
    @GetMapping("/studygroup/member")
    public List<StudyGroupMemberDTO> getAllStudyGroupMember() {
        List<StudyGroupMemberDTO> studyGroupMemberDTOList = studyGroupMemberService.viewAllStudyGroupMember();
        return studyGroupMemberDTOList;
    }
}
