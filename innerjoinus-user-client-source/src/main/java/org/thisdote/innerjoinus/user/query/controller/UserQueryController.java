package org.thisdote.innerjoinus.user.query.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.user.dto.UserDTO;
import org.thisdote.innerjoinus.user.query.service.UserQueryService;
import org.thisdote.innerjoinus.user.vo.RequestUser;
import org.thisdote.innerjoinus.user.vo.ResponseUser;
import org.thisdote.innerjoinus.user.vo.ResponseUserFeignArticlesAndReplies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserQueryController {

    private final ModelMapper mapper;
    private final UserQueryService userQueryService;

    @Autowired
    public UserQueryController(ModelMapper mapper, UserQueryService userQueryService) {
        this.mapper = mapper;
        this.userQueryService = userQueryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseUser>> getAllUser() {
        List<UserDTO> userList = userQueryService.selectAllUser();
        List<ResponseUser> responseUserList = new ArrayList<>();

        if (userList != null) {
            responseUserList = userList.stream().map(userDTO -> mapper.map(userDTO, ResponseUser.class)).toList();
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseUserList);
    }

    @GetMapping("/code/{userCode}")
    public ResponseEntity<ResponseUser> getUserByUserCode(@PathVariable("userCode") Integer userCode) {
        UserDTO selectedUser = userQueryService.selectUserByUserCode(userCode);
        ResponseUser responseUser = new ResponseUser();

        if (selectedUser != null) {
            responseUser = mapper.map(selectedUser, ResponseUser.class);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @GetMapping("/code/with_articles_and_replies/{userCode}")
    public ResponseEntity<ResponseUserFeignArticlesAndReplies> getUserByUserCodeFeignArticlesAndReplies(@PathVariable("userCode") Integer userCode) {
        UserDTO userDTO = userQueryService.getUserByUserCodeFeignArticlesAndReplies(userCode);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ResponseUserFeignArticlesAndReplies returnValue = mapper.map(userDTO, ResponseUserFeignArticlesAndReplies.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<ResponseUser> getUserByUserId(@PathVariable("userId") String userId) {
        UserDTO selectedUser = userQueryService.selectUserByUserId(userId);
        ResponseUser responseUser = new ResponseUser();

        if (selectedUser != null) {
            responseUser = mapper.map(selectedUser, ResponseUser.class);
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> registUser(@RequestBody RequestUser user) {
        System.out.println("user = " + user);
        UserDTO requestSignUpUser = mapper.map(user, UserDTO.class);
        UserDTO returnedUser = userQueryService.createUser(requestSignUpUser);
        Map<String, Object> registrationResultResponse = new HashMap<>();

        if (returnedUser == null) {
            registrationResultResponse.put("message", "회원 등록 실패");
        } else {
            registrationResultResponse.put("resultCode", 200);
            registrationResultResponse.put("message", "회원 등록 성공");
            registrationResultResponse.put("result", mapper.map(returnedUser, ResponseUser.class));
        }

        return ResponseEntity.status(HttpStatus.OK).body(registrationResultResponse);
    }
}
