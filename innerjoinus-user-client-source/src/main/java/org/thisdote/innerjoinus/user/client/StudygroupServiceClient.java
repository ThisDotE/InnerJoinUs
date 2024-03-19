package org.thisdote.innerjoinus.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thisdote.innerjoinus.user.vo.ResponseStudyGroup;

import java.util.List;
import java.util.Map;

@FeignClient(name = "INNERJOINUS-STUDYGROUP-MEMBER", url = "localhost:8000")
public interface StudygroupServiceClient {
    @GetMapping("/studygroup-member/studygroup/user/{userCode}")
    public List<ResponseStudyGroup> selectAllStudyGroupByUser(@PathVariable("userCode") Integer userCode);
}
