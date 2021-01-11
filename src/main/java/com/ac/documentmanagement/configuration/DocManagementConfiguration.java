package com.ac.documentmanagement.configuration;

import com.ac.documentmanagement.exception.RestResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class DocManagementConfiguration {

//  @Bean
//  public RestTemplate restTemplate() {
//    return
//            new RestTemplateBuilder()
//                    .errorHandler(new RestResponseErrorHandler())
//                    .build();
//  }
  @Bean
  public RestTemplate restTemplateConfig() {
    RestTemplate rest = new RestTemplate();
    rest.getInterceptors().add((request, body, execution) -> {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null) {
        return execution.execute(request, body);
      }

      if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
        return execution.execute(request, body);
      }

      AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
      request.getHeaders().setBearerAuth(token.getTokenValue());
      return execution.execute(request, body);
    });
    return rest;
  }
}
