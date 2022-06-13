package com.example.batis.exception.regions.exceptions;


import lombok.Getter;

@Getter
public class RegionException extends RuntimeException {
    private final String reason;

    public RegionException(String message, String reason, Object... params) {
        super(message.formatted(params));
        this.reason = reason;
    }

}
