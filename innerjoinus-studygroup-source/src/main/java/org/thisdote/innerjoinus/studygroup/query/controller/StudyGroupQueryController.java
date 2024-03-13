package org.thisdote.innerjoinus.studygroup.query.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.studygroup.dto.StudyGroupDTO;
import org.thisdote.innerjoinus.studygroup.query.service.StudyGroupService;

import java.util.List;

@RestController
@RequestMapping("/")
public class StudyGroupQueryController {

    private Environment env;
    private ModelMapper mapper;
    private StudyGroupService studyGroupService;

    @Autowired
    public StudyGroupQueryController(Environment env,
                                     ModelMapper mapper, StudyGroupService studyGroupService) {
        this.env = env;
        this.mapper = mapper;
        this.studyGroupService = studyGroupService;
    }

    @GetMapping("/studygroup")
    public List<StudyGroupDTO> getAllStudyGroup() {
        List<StudyGroupDTO> studyGroupDTOList = studyGroupService.viewAllStudyGroup();
        return studyGroupDTOList;
    }
}
