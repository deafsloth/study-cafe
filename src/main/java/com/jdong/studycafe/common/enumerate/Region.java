package com.jdong.studycafe.common.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Region {
    SEOUL("REGION_SEOUL","서울"),
    GYEONGGI("REGION_GYEONGGI","경기도");

    private final String key;
    private final String title;
}
