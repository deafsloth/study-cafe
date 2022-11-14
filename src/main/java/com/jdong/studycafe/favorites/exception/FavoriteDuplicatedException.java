package com.jdong.studycafe.favorites.exception;

import com.jdong.studycafe.common.error.exception.ErrorCode;
import com.jdong.studycafe.common.error.exception.InvalidValueException;
import com.jdong.studycafe.favorites.domain.Favorite;

public class FavoriteDuplicatedException extends InvalidValueException {
    public FavoriteDuplicatedException(final String favorite) {
        super(favorite, ErrorCode.FAVORITE_DUPLICATION);
    }
}
