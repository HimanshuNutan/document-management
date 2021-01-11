package com.ac.documentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

public class DocumentData {

  private String docId;
  private String docName;
  private String docLocation;

  public DocumentData() {
  }

  public DocumentData(String docId, String docName, String docLocation) {
    this.docId = docId;
    this.docName = docName;
    this.docLocation = docLocation;
  }

  public String getDocName() {
    return docName;
  }

  public void setDocName(String docName) {
    this.docName = docName;
  }

  public String getDocLocation() {
    return docLocation;
  }

  public void setDocLocation(String docLocation) {
    this.docLocation = docLocation;
  }

  public String getDocId() {
    return docId;
  }

  public void setDocId(String docId) {
    this.docId = docId;
  }

}
