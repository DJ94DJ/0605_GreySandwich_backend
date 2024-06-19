package com.grey.sand.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private int id;

    private String email;

    private String nickname;

    private String password;


}
