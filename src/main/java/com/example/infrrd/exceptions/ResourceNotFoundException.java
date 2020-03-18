package com.example.infrrd.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception implements Serializable{
 /**
	 * 
	 */
private static final long serialVersionUID = -2050756953864071998L;

public ResourceNotFoundException(String message){
     super(message);
    }
}