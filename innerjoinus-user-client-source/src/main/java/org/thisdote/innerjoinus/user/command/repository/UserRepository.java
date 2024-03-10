package org.thisdote.innerjoinus.user.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thisdote.innerjoinus.user.command.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserId(String userId);

    UserEntity findByUserEmail(String email);
}
