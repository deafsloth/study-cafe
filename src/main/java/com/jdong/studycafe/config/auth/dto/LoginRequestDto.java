package com.jdong.studycafe.config.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginRequestDto {

    @NotBlank(message = "username 값은 비어었을 수 없습니다.")
    @Size(min = 4, max = 15,message = "username 값은 4이상 15이하의 스트링값입니다.")
    private String username;

    @NotBlank(message = "password 값은 비어었을 수 없습니다.")
    @Size(min = 6, max = 20, message = "password 값은 4이상 15이하의 스트링값입니다.")
    private String password;
}
