package org.thisdote.innerjoinus.user.query.service;

import org.thisdote.innerjoinus.user.dto.UserDTO;

import java.util.List;

//public interface UserQueryService extends UserDetailsService {
public interface UserQueryService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> selectAllUser();

    UserDTO selectUserByUserCode(int userCode);

    UserDTO selectUserByUserId(String userId);

    UserDTO getUserDetailsByEmail(String email);

    UserDTO getUserByUserCodeFeignArticlesAndReplies(Integer userCode);

    UserDTO selectUserByLoginCode(String loginCode);
}
