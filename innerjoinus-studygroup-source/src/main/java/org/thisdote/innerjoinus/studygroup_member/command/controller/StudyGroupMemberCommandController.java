package org.thisdote.innerjoinus.studygroup_member.command.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.studygroup_member.command.service.StudyGroupMemberCommandService;
import org.thisdote.innerjoinus.studygroup_member.command.vo.*;
import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;

@RestController
@RequestMapping("/")
public class StudyGroupMemberCommandController {

    private final ModelMapper modelMapper;
    private final StudyGroupMemberCommandService studyGroupMemberCommandService;

    @Autowired
    public StudyGroupMemberCommandController(ModelMapper modelMapper,
                                             StudyGroupMemberCommandService studyGroupMemberCommandService) {
        this.modelMapper = modelMapper;
        this.studyGroupMemberCommandService = studyGroupMemberCommandService;
    }

    // 서버와 연결 확인용 테스트
//    @GetMapping("/a")
//    public String status() {
//        return "Server at" + env.getProperty("local.server.port") +
//                ", swcamp.message: " + env.getProperty("swcamp.message");
//    }

    // 스터디원 추가하기 - regist(insert)
    @PostMapping("/studygroup/member/regist")
    public ResponseEntity<ResponseRegistStudyGroupMember> registStudyGroupMember
                                                    (@RequestBody RequestRegistStudyGroupMember studyGroupMember) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StudyGroupMemberDTO studyGroupMemberDTO = modelMapper.map(studyGroupMember, StudyGroupMemberDTO.class);

        StudyGroupMemberDTO returnedDTO = studyGroupMemberCommandService.registStudyGroupMember(studyGroupMemberDTO);

        ResponseRegistStudyGroupMember responseRegistStudyGroupMember = new ResponseRegistStudyGroupMember();

        responseRegistStudyGroupMember.setStudyGroupId(studyGroupMember.getStudyGroupId());
        responseRegistStudyGroupMember.setStudyGroupRole(studyGroupMember.getStudyGroupRole());
        responseRegistStudyGroupMember.setUserCode(studyGroupMember.getUserCode());

        responseRegistStudyGroupMember.setStudyGroupRegistDate(studyGroupMemberDTO.getStudyGroupRegistDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(returnedDTO, ResponseRegistStudyGroupMember.class));


    }

    // 스터디원 수정하기 - modify(update)
    @PostMapping("/studygroup/member/modify")
    public ResponseEntity<ResponseModifyStudyGroupMember> modifyStudyGroupMember (@RequestBody RequestModifyStudyGroupMember
                                                                                        modifyStudyGroupMember) {
        StudyGroupMemberDTO studyGroupMemberDTO = modelMapper.map(modifyStudyGroupMember, StudyGroupMemberDTO.class);
        studyGroupMemberCommandService.modifyStudyGroupMemberRole(studyGroupMemberDTO);

        ResponseModifyStudyGroupMember responseModifyStudyGroupMember = new ResponseModifyStudyGroupMember();
        responseModifyStudyGroupMember.setStudyGroupId(studyGroupMemberDTO.getStudyGroupId());
        responseModifyStudyGroupMember.setStudyGroupRole(0);
        responseModifyStudyGroupMember.setStudyGroupRegistDate(studyGroupMemberDTO.getStudyGroupRegistDate());
        responseModifyStudyGroupMember.setUserCode(studyGroupMemberDTO.getUserCode());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseModifyStudyGroupMember);
    }

    // 스터디원 삭제하기 - delete
    @GetMapping("/studygroup/member/delete")
    public void deleteStudyGroupMember(){}

    @PostMapping("/studygroup/member/delete")
    public ResponseEntity<ResponseDeleteStudyGroupMember> deleteStudyGroupMember (@RequestBody RequestDeleteStudyGroupMember
                                                                                              deleteStudyGroupMemberId) {
//        StudyGroupMemberDTO studyGroupMemberDTO = modelMapper.map(deleteStudyGroupMemberId, StudyGroupMemberDTO.class);
        String message = studyGroupMemberCommandService.deleteStudyGroupMemberId(deleteStudyGroupMemberId.getStudyGroupMemberId());

        ResponseDeleteStudyGroupMember responseDeleteStudyGroupMember = new ResponseDeleteStudyGroupMember();

        responseDeleteStudyGroupMember.setMessage(message);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDeleteStudyGroupMember);
    }

    @GetMapping("/studygroupmember/user/{studyGroupMemberId}")
    public ResponseEntity<ResponseStudyGroupMemberUser> selectStudyGroupMemberUser(@PathVariable("studyGroupMemberId") int studyGroupMemberId) {
        StudyGroupMemberDTO studyGroupMemberDTO = studyGroupMemberCommandService.selectStudyGroupMemberUser(studyGroupMemberId);

        ResponseStudyGroupMemberUser returnValue = modelMapper.map(studyGroupMemberDTO, ResponseStudyGroupMemberUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
