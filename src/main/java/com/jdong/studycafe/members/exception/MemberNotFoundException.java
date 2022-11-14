package com.jdong.studycafe.members.exception;

import com.jdong.studycafe.common.error.exception.EntityNotFoundException;
import com.jdong.studycafe.common.error.exception.ErrorCode;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(final Long memberId) {
        super("memberId" + memberId.toString() + " is not found", ErrorCode.MEMBER_NOT_FOUND);
    }
}
