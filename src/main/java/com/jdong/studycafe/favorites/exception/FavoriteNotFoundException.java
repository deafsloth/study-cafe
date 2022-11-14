package com.jdong.studycafe.favorites.exception;

import com.jdong.studycafe.common.error.exception.EntityNotFoundException;
import com.jdong.studycafe.common.error.exception.ErrorCode;

public class FavoriteNotFoundException extends EntityNotFoundException {


    public FavoriteNotFoundException(Long favoriteId) {
        super("cafeId: " + favoriteId.toString() + " is not found", ErrorCode.FAVORITE_NOT_FOUND);
    }
}
