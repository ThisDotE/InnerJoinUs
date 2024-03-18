package org.thisdote.innerjoinus.user.command.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @PutMapping("/")
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정 API")
    public ResponseEntity<Map<String, Object>> modifyUser(@RequestBody RequestUser user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);

        userCommandService.modifyUser(userDTO);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("resultCode", 200);
        responseMap.put("message", "회원 정보 수정 성공");

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    @DeleteMapping("/")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 처리 API")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody RequestUser user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);

        userCommandService.deleteUser(userDTO.getUserCode());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("resultCode", 200);
        responseMap.put("message", "회원 탈퇴 성공");

        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }
}
