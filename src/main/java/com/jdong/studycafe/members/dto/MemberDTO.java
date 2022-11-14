package com.jdong.studycafe.members.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String name;
    private String email;

    private int specialCredit;
    private int generalCredit;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;


}
