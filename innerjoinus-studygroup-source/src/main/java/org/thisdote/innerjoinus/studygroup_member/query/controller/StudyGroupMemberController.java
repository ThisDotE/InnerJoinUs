package org.thisdote.innerjoinus.studygroup_member.query.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    // 전체 멤버(스터디장, 스터디원) List에 담아서 조회하기
    @GetMapping("/studygroupmember")
    @Operation(summary = "스터디 그룹원 전체 조회", description = "스터디 그룹원 전체 조회 API")
    public List<StudyGroupMemberDTO> getAllStudyGroupMember() {
        List<StudyGroupMemberDTO> studyGroupMemberDTOList = studyGroupMemberService.viewAllStudyGroupMember();
        return studyGroupMemberDTOList;
    }
}
