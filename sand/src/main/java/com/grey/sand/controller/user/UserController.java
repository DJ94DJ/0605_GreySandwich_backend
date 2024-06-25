package com.grey.sand.controller.user;

import com.grey.sand.dto.user.UserDto;
import com.grey.sand.entity.user.UserEntity;
import com.grey.sand.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        try{
            // email 중복확인
            if (userService.checkEmail(userDto.getEmail())) {
                return ResponseEntity.badRequest().body("이미 존재하는 이메일입니다.");
            }
            if (userService.checkNickName(userDto.getNickname())) {
                return ResponseEntity.badRequest().body("이미 존재하는 닉네임입니다.");
            }

            UserEntity user = UserEntity.builder()
                    .email(userDto.getEmail())
                    .nickname(userDto.getNickname())
                    .password(userDto.getPassword())
                    .build();

            UserEntity newUser = userService.signUpUser(user);
            return ResponseEntity.ok().body("회원가입성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }


}
