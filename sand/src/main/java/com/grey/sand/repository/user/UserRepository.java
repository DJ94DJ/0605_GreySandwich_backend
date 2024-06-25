package com.grey.sand.repository.user;

import com.grey.sand.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // 메서드는 다시 정의하기
    UserEntity findByEmail(String email);

    UserEntity findByNickname(String nickname);
}
