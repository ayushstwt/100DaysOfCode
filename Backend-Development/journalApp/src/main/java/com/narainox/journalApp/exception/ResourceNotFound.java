package com.narainox.journalApp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException{
    private String resource;
    private String type;
    private Object value;

    public ResourceNotFound(String resource, String type, Object value) {
        super(String.format("%s is Not Found With %s : %s",resource,type,value));
        this.resource = resource;
        this.type = type;
        this.value = value;
    }
}
