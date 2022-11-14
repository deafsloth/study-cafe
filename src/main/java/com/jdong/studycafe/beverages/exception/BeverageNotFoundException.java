package com.jdong.studycafe.beverages.exception;

import com.jdong.studycafe.common.error.exception.EntityNotFoundException;
import com.jdong.studycafe.common.error.exception.ErrorCode;

public class BeverageNotFoundException extends EntityNotFoundException {

    public BeverageNotFoundException(Long beverageId) {
        super("beverageId: " + beverageId.toString() + " is not found", ErrorCode.BAVERAGE_NOT_FOUND);
    }
}
