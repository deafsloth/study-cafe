package com.jdong.studycafe.orders.exception;

import com.jdong.studycafe.common.error.exception.ErrorCode;
import com.jdong.studycafe.common.error.exception.InvalidValueException;

public class NotEnoughMoneyException extends InvalidValueException {

    public NotEnoughMoneyException(final String userId) {
        super(userId, ErrorCode.NOT_ENOUGH_CREDIT);
    }
}
