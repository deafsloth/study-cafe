package com.jdong.studycafe.cafes.exception;

import com.jdong.studycafe.common.error.exception.EntityNotFoundException;
import com.jdong.studycafe.common.error.exception.ErrorCode;

public class CafeNotFoundException extends EntityNotFoundException {

    public CafeNotFoundException(Long cafeId) {
        super("cafeId: " + cafeId.toString() + " is not found", ErrorCode.CAFE_NOT_FOUND);

    }
}
