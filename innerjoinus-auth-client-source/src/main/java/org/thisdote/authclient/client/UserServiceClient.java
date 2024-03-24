package org.thisdote.authclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thisdote.authclient.vo.RequestUser;
import org.thisdote.authclient.vo.ResponseUser;

import java.util.Map;

@FeignClient(name = "INNERJOINUS-USER", url ="localhost:8000")
public interface UserServiceClient {

    @GetMapping("/user/login-code/{loginCode}")
    ResponseUser getUserInfo(@PathVariable("loginCode") String loginCode);

    @PostMapping("/user/")
    Map<String, Object> registUser(@RequestBody RequestUser user);

}
