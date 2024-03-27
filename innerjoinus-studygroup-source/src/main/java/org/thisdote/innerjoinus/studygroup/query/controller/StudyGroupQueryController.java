package org.thisdote.innerjoinus.studygroup.query.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.studygroup.command.entity.StudyGroupEntity;
import org.thisdote.innerjoinus.studygroup.command.repository.StudyGroupRepository;
import org.thisdote.innerjoinus.studygroup.dto.StudyGroupDTO;
import org.thisdote.innerjoinus.studygroup.query.service.StudyGroupService;
import org.thisdote.innerjoinus.studygroup.query.vo.ResponseStudyGroup;
import org.thisdote.innerjoinus.studygroup_member.query.service.StudyGroupMemberService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class StudyGroupQueryController {

    private Environment env;
    private ModelMapper mapper;
    private StudyGroupService studyGroupService;
    private StudyGroupMemberService studyGroupMemberService;
    private StudyGroupRepository studyGroupRepository;

    @Autowired
    public StudyGroupQueryController(Environment env,
                                     ModelMapper mapper,
                                     StudyGroupService studyGroupService,
                                     StudyGroupMemberService studyGroupMemberService,
                                     StudyGroupRepository studyGroupRepository) {
        this.env = env;
        this.mapper = mapper;
        this.studyGroupService = studyGroupService;
        this.studyGroupMemberService = studyGroupMemberService;
        this.studyGroupRepository = studyGroupRepository;
    }

    @GetMapping("/studygroup")
    public List<StudyGroupDTO> getAllStudyGroup() {
        List<StudyGroupDTO> studyGroupDTOList = studyGroupService.viewAllStudyGroup();
        return studyGroupDTOList;
    }

    @GetMapping("/studygroup/{studygroupId}")
    public ResponseEntity<ResponseStudyGroup> selectStudyGroupByStudyGroupId(@PathVariable("studygroupId") int studygroupId) {
        StudyGroupDTO studyGroupDTO = studyGroupService.selectStudyGroupByStudyGroupId(studygroupId);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseStudyGroup returnValue = mapper.map(studyGroupDTO, ResponseStudyGroup.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/studygroup/user/{userCode}")
    public ResponseEntity<List<ResponseStudyGroup>> selectAllStudyGroupByUser(@PathVariable("userCode") Integer userCode) {
        List<StudyGroupDTO> studyGroupDTOList = studyGroupService.selectAllStudyGroupByUser(userCode);
        List<ResponseStudyGroup> responseStudyGroupList = new ArrayList<>();

        if (studyGroupDTOList != null) {
            responseStudyGroupList = studyGroupDTOList.stream().map(StudyGroupDTO -> mapper.map(StudyGroupDTO, ResponseStudyGroup.class)).toList();
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseStudyGroupList);
    }
}
