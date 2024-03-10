package org.thisdote.innerjoinus.user.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thisdote.innerjoinus.user.query.aggregate.UserQueryEntity;

public interface UserQueryRepository extends JpaRepository<UserQueryEntity, Integer> {
}
