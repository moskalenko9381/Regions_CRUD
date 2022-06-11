package com.example.batis.exception;

import com.example.batis.exception.regions.exceptions.EmptyListOfRegionsException;
import com.example.batis.exception.regions.exceptions.NoSuchElementException;
import com.example.batis.exception.regions.exceptions.RegionAlreadyExistsException;
import com.example.batis.exception.regions.exceptions.RegionDeletionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ExceptionMapper {

    private static final Map<Class<?>, ResponseEntity<?>> MAPPER;

    static {
        MAPPER = new HashMap<>();
        MAPPER.put(NoSuchElementException.class, ResponseEntity.badRequest().build());
        MAPPER.put(EmptyListOfRegionsException.class, ResponseEntity.noContent().build());
        MAPPER.put(RegionDeletionException.class, ResponseEntity.badRequest().build());
        MAPPER.put(RegionAlreadyExistsException.class, ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


    public static ResponseEntity<?> map(Exception exception) {
        if (!MAPPER.containsKey(exception.getClass())) {
            return ResponseEntity.internalServerError().build();
        }
        return MAPPER.get(exception.getClass());
    }
}
