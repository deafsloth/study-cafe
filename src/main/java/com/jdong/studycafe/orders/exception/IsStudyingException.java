package com.jdong.studycafe.orders.exception;

import com.jdong.studycafe.common.error.exception.ErrorCode;
import com.jdong.studycafe.common.error.exception.InvalidValueException;

public class IsStudyingException extends InvalidValueException {

    public IsStudyingException(final String userId) {
        super(userId, ErrorCode.MEMBER_IS_STUDYING);
    }
}
