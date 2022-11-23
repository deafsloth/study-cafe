package com.jdong.studycafe.orders.exception;

import com.jdong.studycafe.common.error.exception.ErrorCode;
import com.jdong.studycafe.common.error.exception.InvalidValueException;

public class StudyRecordDuplicateException extends InvalidValueException {

    public StudyRecordDuplicateException(final String userId) {
        super(userId, ErrorCode.MEMBER_HAS_DUPLICATE_STUDY_RECORD);
    }
}
