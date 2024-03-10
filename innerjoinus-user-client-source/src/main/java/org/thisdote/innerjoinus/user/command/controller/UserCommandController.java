package org.thisdote.innerjoinus.user.command.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thisdote.innerjoinus.user.command.service.UserCommandService;
import org.thisdote.innerjoinus.user.dto.UserDTO;
import org.thisdote.innerjoinus.user.query.service.UserQueryService;
import org.thisdote.innerjoinus.user.vo.RequestUser;
import org.thisdote.innerjoinus.user.vo.ResponseUser;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserCommandController {

    private final ModelMapper mapper;
    private final UserCommandService userCommandService;

    @Autowired
    public UserCommandController(
            ModelMapper mapper,
            UserCommandService userCommandService
    ) {
        this.mapper = mapper;
        this.userCommandService = userCommandService;
    }

    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> registUser(@RequestBody RequestUser user) {
        UserDTO requestSignUpUser = mapper.map(user, UserDTO.class);
        UserDTO returnedUser = userCommandService.registUser(requestSignUpUser);
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

    @PostMapping("/modify")
    public ResponseEntity<Map<String, Object>> modifyUser(@RequestBody RequestUser user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);

        userCommandService.modifyUser(userDTO);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("resultCode", 200);
        responseMap.put("message", "회원 정보 수정 성공");

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody RequestUser user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);

        userCommandService.deleteUser(userDTO.getUserCode());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("resultCode", 200);
        responseMap.put("message", "회원 탈퇴 성공");

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
