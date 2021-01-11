package com.ac.documentmanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class RestResponseErrorHandler extends DefaultResponseErrorHandler {


  @Override
  public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
    log.info("");
  }
}
