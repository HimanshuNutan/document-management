package com.ac.documentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

public class MetadataInfo {

  private String docId;
  private String docType;
  private String docSize;

  public MetadataInfo() {
  }

  public MetadataInfo(String docId, String docType, String docSize) {
    this.docId = docId;
    this.docType = docType;
    this.docSize = docSize;
  }

  public String getDocId() {
    return docId;
  }

  public void setDocId(String docId) {
    this.docId = docId;
  }

  public String getDocType() {
    return docType;
  }

  public void setDocType(String docType) {
    this.docType = docType;
  }

  public String getDocSize() {
    return docSize;
  }

  public void setDocSize(String docSize) {
    this.docSize = docSize;
  }


}
