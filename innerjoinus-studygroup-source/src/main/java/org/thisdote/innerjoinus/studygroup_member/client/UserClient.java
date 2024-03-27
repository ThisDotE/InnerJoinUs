package org.thisdote.innerjoinus.studygroup_member.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thisdote.innerjoinus.studygroup_member.command.vo.ResponseUser;

@FeignClient(name="INNERJOINUS-USER", url = "localhost:8000")
public interface UserClient {
    @GetMapping("/user/users/{userCode}")
    ResponseUser getAllUser(@PathVariable("userCode") Integer userCode);
}
