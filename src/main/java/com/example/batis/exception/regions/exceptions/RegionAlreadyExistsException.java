package com.example.batis.exception.regions.exceptions;

import com.example.batis.exception.ErrorReason;

import java.io.Serial;
import java.util.Arrays;

public class RegionAlreadyExistsException extends RegionException {
    @Serial
    private static final long serialVersionUID = 1252649479207495830L;

    public RegionAlreadyExistsException(Object... params) {
        super("Element with parameters %s already exists", ErrorReason.NAME_ALREADY_EXISTS.name(),
                Arrays.toString(params));
    }
}
