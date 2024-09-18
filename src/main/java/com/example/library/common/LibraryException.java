package com.example.library.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LibraryException extends RuntimeException {
    private Integer code;

    public LibraryException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
