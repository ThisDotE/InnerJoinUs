package org.thisdote.innerjoinus.user.query.repository;

import org.apache.ibatis.annotations.Mapper;
import org.thisdote.innerjoinus.user.dto.UserDTO;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDTO> selectAllUser();

    List<UserDTO> selectUserId(String userId);

    UserDTO selectUserByUserCode(int userCode);

    List<UserDTO> selectUsersByResignStatus(int resignStatus);

    UserDTO selectUserByUserId(String userId);
}
