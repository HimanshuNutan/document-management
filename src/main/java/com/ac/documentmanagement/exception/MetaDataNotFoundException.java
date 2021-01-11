package com.ac.documentmanagement.exception;

public class MetaDataNotFoundException extends RuntimeException {
  public MetaDataNotFoundException(String docId) {
    super("Meta Data Not Found for " + docId);
  }
}
