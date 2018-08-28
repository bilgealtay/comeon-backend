package com.comeon.gamelove.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GameLikedAlreadyException extends Exception {
    public GameLikedAlreadyException(String exception) {
        super(exception);
    }
}
