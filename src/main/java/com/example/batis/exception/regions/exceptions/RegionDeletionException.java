package com.example.batis.exception.regions.exceptions;

import com.example.batis.exception.ErrorReason;

import java.io.Serial;

public class RegionDeletionException extends RegionException {
    @Serial
    private static final long serialVersionUID = 7776700894672106026L;

    public RegionDeletionException() {
        super("Can't delete element on given id.", ErrorReason.DELETION_ERROR.name());
    }
}
