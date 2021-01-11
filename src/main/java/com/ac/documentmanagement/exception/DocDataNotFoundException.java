package com.ac.documentmanagement.exception;

public class DocDataNotFoundException extends RuntimeException {
  public DocDataNotFoundException(String docId) {
    super("Doc Data Not Found for " + docId);
  }
}
