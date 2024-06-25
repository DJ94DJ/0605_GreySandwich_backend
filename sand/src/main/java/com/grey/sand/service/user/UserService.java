package com.grey.sand.service.user;

import com.grey.sand.entity.user.UserEntity;
import com.grey.sand.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private SecureRandom random = new SecureRandom();

    public UserEntity signUpUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new RuntimeException("유저 엔티티가 없어요");
        }
        // 솔트값이 Bcryto패스워드
        String salt = new BigInteger(130, random).toString();
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword() + salt);

        UserEntity user = UserEntity.builder()
                .email(userEntity.getEmail())
                .nickname(userEntity.getNickname())
                .password(userEntity.getPassword())
                .salt(salt)
                .build();

        return userRepository.save(user);

    }

    // email 중복 확인
    public boolean checkEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)).isPresent();
    }

    // nickname 중복 확인
    public boolean checkNickName(String nickname) {
        return Optional.ofNullable(userRepository.findByNickname(nickname)).isPresent();

    }


}
