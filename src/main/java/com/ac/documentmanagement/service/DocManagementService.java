package com.ac.documentmanagement.service;

import java.net.URI;


import com.ac.documentmanagement.configuration.ApplicationProperties;
import com.ac.documentmanagement.exception.DocDataNotFoundException;
import com.ac.documentmanagement.exception.MetaDataNotFoundException;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ac.documentmanagement.model.Document;
import com.ac.documentmanagement.model.DocumentData;
import com.ac.documentmanagement.model.MetadataInfo;

@Service
@Slf4j
public class DocManagementService {

  @Autowired
  private RestTemplate restTemplateConfig;

  @Autowired
  public ApplicationProperties properties;

  @Autowired
  public DocManagementService() {
  }

  @HystrixCommand(fallbackMethod = "getDocInfoFallback")
  public DocumentData getDocInfo(String docId) {
    log.info("getting doc info for docId " + docId + " url is " + properties.getEndpoint().getDocInfoUrl() + "/docinfo/" + docId);
    DocumentData documentData = restTemplateConfig.getForObject(properties.getEndpoint().getDocInfoUrl() + "/docinfo/" + docId, DocumentData.class);
    if (documentData.getDocId() == null) {
      throw new DocDataNotFoundException(docId);
    }
    log.info("got documentDatausing document data service ");
    return documentData;
  }

  public DocumentData getDocInfoFallback(String docId) {
    return new DocumentData("Record Not Found", "", "");
  }

  @HystrixCommand(fallbackMethod = "getMetaInfoFallback")
  public MetadataInfo getMetaInfo(String docId) {
    log.info("getting meta info for docId " + docId + " url is " + properties.getEndpoint().getMetaInfoUrl() + "/metainfo/" + docId);
    MetadataInfo metadataInfo = restTemplateConfig.getForObject(properties.getEndpoint().getMetaInfoUrl() + "/metainfo/" + docId, MetadataInfo.class);
    if (metadataInfo.getDocId() == null) {
      throw new MetaDataNotFoundException(docId);
    }
    log.info("got metadata using metadata info service ");
    return metadataInfo;
  }

  public MetadataInfo getMetaInfoFallback(String docId) {
    return new MetadataInfo("", "", "");
  }

  public void addDocument(Document document) {
    DocumentData documentData = new DocumentData();
    MetadataInfo metadataInfo = new MetadataInfo();
    documentData.setDocId(document.getDocId());
    documentData.setDocName(document.getDocName());
    documentData.setDocLocation(document.getDocLoc());
    metadataInfo.setDocId(document.getDocId());
    metadataInfo.setDocType(document.getDocType());
    metadataInfo.setDocSize(document.getDocSize());
    addDocInfo(documentData);
    addMetaInfo(metadataInfo);
  }

  public void addDocInfo(DocumentData documentData) {
    try {
      HttpHeaders header = new HttpHeaders();
      ResponseEntity<Integer> entity1 = restTemplateConfig.postForEntity(
              new URI(properties.getEndpoint().getDocInfoUrl() + "/docinfo/"),
              new HttpEntity<DocumentData>(documentData, header),
              Integer.class);
      log.info("document info adding " + entity1);
    } catch (Exception e) {
      log.error("Error in adding Doc Info", e);
    }
  }

  public void addMetaInfo(MetadataInfo metadataInfo) {
    try {
      HttpHeaders header = new HttpHeaders();
      ResponseEntity<Integer> entity3 = restTemplateConfig.postForEntity(
              new URI(properties.getEndpoint().getMetaInfoUrl() + "/metainfo/"),
              new HttpEntity<MetadataInfo>(metadataInfo, header),
              Integer.class);
      log.info("metadata info adding " + entity3);
    } catch (Exception e) {
      log.error("Error in adding MetaInfo", e);
    }
  }
}
