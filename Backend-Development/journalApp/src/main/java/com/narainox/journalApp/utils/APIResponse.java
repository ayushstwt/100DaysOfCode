package com.narainox.journalApp.utils;

import lombok.Data;

@Data
public class APIResponse<T> {
    T data;
    private String mesaage;
}
