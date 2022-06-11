package com.example.batis.exception.regions.exceptions;

import com.example.batis.exception.ErrorReason;

import java.io.Serial;

public class EmptyListOfRegionsException extends RegionException {

    @Serial
    private static final long serialVersionUID = 1252649479207495830L;

    public EmptyListOfRegionsException() {
        super("List of regions is empty", ErrorReason.EMPTY_LIST.name());
    }
}