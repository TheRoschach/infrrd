package com.example.infrrd.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
	private LocalDateTime timestamp;
	 private String message;
	 private String details;
	 public ErrorDetails( String message, String details) {
	  super();
	  this.timestamp = LocalDateTime.now();
	  this.message = message;
	  this.details = details;
	 }
	 public LocalDateTime getTimestamp() {
	  return timestamp;
	 }
	 public String getMessage() {
	  return message;
	 }
	 public String getDetails() {
	  return details;
	 }

}
