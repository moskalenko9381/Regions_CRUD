package com.example.batis.exception.regions.exceptions;

import com.example.batis.exception.ErrorReason;

import java.io.Serial;
import java.util.Arrays;

public class NoSuchElementException extends RegionException {

    @Serial
    private static final long serialVersionUID = 2837811265913164509L;

    public NoSuchElementException(Object... params) {
        super("Element with parameters " + Arrays.toString(params) + " not found", ErrorReason.NO_SUCH_ELEMENT.name());
    }
}
