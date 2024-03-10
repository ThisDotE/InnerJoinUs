package org.thisdote.innerjoinus.studygroup.command.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.studygroup.command.service.StudyGroupCommandService;
import org.thisdote.innerjoinus.studygroup.command.vo.*;
import org.thisdote.innerjoinus.studygroup.dto.StudyGroupCommandDTO;
import org.thisdote.innerjoinus.studygroup_member.command.service.StudyGroupMemberCommandService;
import org.thisdote.innerjoinus.studygroup_member.dto.StudyGroupMemberDTO;

@RestController
@RequestMapping("/")
public class StudyGroupCommandController {

    private ModelMapper mapper;
    private StudyGroupCommandService studyGroupCommandService;

    @Autowired
    public StudyGroupCommandController(ModelMapper mapper, StudyGroupCommandService studyGroupCommandService) {
        this.mapper = mapper;
        this.studyGroupCommandService = studyGroupCommandService;
    }

    /* 필기. Insert */
    @PostMapping("/studygroup/insert")
    @Operation(summary = "스터디 그룹 생성", description = "스터디 그룹 생성 API")
    public ResponseEntity<ResponseStudyGroup> registStudyGroup(@RequestBody RequestStudyGroup inputStudyGroup) {

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StudyGroupCommandDTO inputStudyGroupDTO = mapper.map(inputStudyGroup, StudyGroupCommandDTO.class);
        StudyGroupCommandDTO studyGroupCommandDTO = studyGroupCommandService.insertStudygroup(inputStudyGroupDTO);

        ResponseStudyGroup responseStudyGroup = new ResponseStudyGroup();

        responseStudyGroup.setStudygroupType(inputStudyGroup.getStudygroupType());
        responseStudyGroup.setStudygroupMemberCount(inputStudyGroup.getStudygroupMemberCount());
        responseStudyGroup.setStudygroupStudyTime(inputStudyGroup.getStudygroupStudyTime());
        responseStudyGroup.setStudygroupContent(inputStudyGroup.getStudygroupContent());

        responseStudyGroup.setStudygroupCreateDate(responseStudyGroup.getStudygroupCreateDate());
        responseStudyGroup.setStudygroupActivationStatus(responseStudyGroup.getStudygroupActivationStatus());
        responseStudyGroup.setStudygroupDeleteStatus(responseStudyGroup.getStudygroupDeleteStatus());

        System.out.println("studyGroupDTO = " + studyGroupCommandDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(studyGroupCommandDTO,ResponseStudyGroup.class));
    }

    /* 필기. Update */
    @PostMapping("/studygroup/update")
    @Operation(summary = "스터디 그룹 수정", description = "스터디 그룹 수정 API")
    public ResponseEntity<ResponseModifyStudyGroup>
                    modifyStudyGroup(@RequestBody RequestModifyStudyGroup modifyStudyGroup){
        StudyGroupCommandDTO studyGroupCommandDTO = mapper.map(modifyStudyGroup, StudyGroupCommandDTO.class);
        System.out.println("studyGroupCommandDTO = " + studyGroupCommandDTO);

        studyGroupCommandService.updateStudyGroup(studyGroupCommandDTO);

        ResponseModifyStudyGroup responseModifyStudyGroup = mapper.map(studyGroupCommandDTO,ResponseModifyStudyGroup.class);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseModifyStudyGroup);
    }

    @PostMapping("/studygroup/delete")
    @Operation(summary = "스터디 그룹 삭제", description = "스터디 그룹 삭제 API")
    public ResponseEntity<ResponseDeleteStudyGroup>
                    deleteStudyGroup(@RequestBody RequestDeleteStudyGroup deleteStudyGroup) {
        StudyGroupCommandDTO studyGroupCommandDTO =mapper.map(deleteStudyGroup, StudyGroupCommandDTO.class);
        System.out.println("studyGroupCommandDTO = " + studyGroupCommandDTO);
        studyGroupCommandService.removeStudyGroup(studyGroupCommandDTO);

        ResponseDeleteStudyGroup responseDeleteStudyGroup = mapper.map(studyGroupCommandDTO, ResponseDeleteStudyGroup.class);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDeleteStudyGroup);
    }

//    @GetMapping("/studygroup-member/user/{}")
//    public ResponseEntity<ResponseStudyGroupMemberUser> selectStudyGroupMemberUser(@PathVariable("studygroupMemberId") int studygroupMemberId) {
//        StudyGroupMemberDTO studyGroupMemberDTO = StudyGroupMemberCommandService.selectStudyGroupMemberUser(studygroupMemberId);
//
//        ResponseStudyGroupMemberUser returnValue = mapper.map(studyGroupMemberDTO, ResponseStudyGroupMemberUser.class);
//        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
//    }
}
