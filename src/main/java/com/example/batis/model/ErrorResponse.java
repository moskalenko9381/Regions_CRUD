package com.example.batis.model;

import com.example.batis.exception.ErrorReason;
import com.example.batis.exception.regions.exceptions.RegionException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String reason;

    public ErrorResponse(Exception exception) {
        message = exception.getMessage();
        if (exception instanceof RegionException) {
            reason = ((RegionException) exception).getReason();
        } else {
            reason = ErrorReason.UNDEFINED.name();
        }
    }
}
