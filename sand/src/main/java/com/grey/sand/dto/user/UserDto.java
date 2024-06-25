package com.grey.sand.dto.user;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;

    private String email;

    private String nickname;

    private String password;

    private String salt;


}
