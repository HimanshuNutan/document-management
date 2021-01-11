package com.ac.documentmanagement.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorStatus {

  REMOTE_NOT_FOUND(502, "BAD GATEWAY"),
  UNKNOWN_ERROR(500, "TEMPORARY SERVER ERROR"),
  RESOURCE_NOT_FOUND(404, "RESOURCE_NOT_FOUND"),
  FORBIDDEN(403, "FORBIDDEN");

  private final int code;
  private final String reason;
}
