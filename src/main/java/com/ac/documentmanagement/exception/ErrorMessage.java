package com.ac.documentmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

  DOC_DATA_NOT_FOUND("Data not found for given doc id"),
  META_DATA_NOT_FOUND("Meta data not found for given doc id"),
  CONNECTION_ISSUE("Problem connecting to other systems");

  private String message;
}
