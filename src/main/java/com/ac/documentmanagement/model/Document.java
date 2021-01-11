package com.ac.documentmanagement.model;

public class Document {

  private String docName;
  private String docLoc;
  private String docId;
  private String docType;
  private String docSize;

  public Document() {

  }

  public Document(String docId, String docName, String docLoc, String docType, String docSize) {
    this.docId = docId;
    this.docName = docName;
    this.docLoc = docLoc;
    this.docType = docType;
    this.docSize = docSize;
  }

  public String getDocName() {
    return docName;
  }

  public void setDocName(String docName) {
    this.docName = docName;
  }

  public String getDocLoc() {
    return docLoc;
  }

  public void setDocLoc(String docLoc) {
    this.docLoc = docLoc;
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
