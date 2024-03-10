package org.thisdote.innerjoinus.studygroup.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thisdote.innerjoinus.studygroup.query.vo.ResponseStudyGroup;

import java.util.List;

@FeignClient(name="innerjoinus-studygroup-member", url="localhost:8000")
public interface StudyGroupClient {

    @GetMapping("/studygroup-member/{studygroupId}")
    List<ResponseStudyGroup> getStudyGroups(@PathVariable("studygroupId") int studygroupId);

}
