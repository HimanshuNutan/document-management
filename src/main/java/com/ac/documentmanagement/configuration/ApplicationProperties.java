package com.ac.documentmanagement.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@ConfigurationProperties(prefix = "app")
@ConstructorBinding
@Getter
@AllArgsConstructor
@Validated
public class ApplicationProperties {

  private Endpoint endpoint;

  @Getter
  @AllArgsConstructor
  @Validated
  public static class Endpoint {

    @NotBlank
    private String docInfoUrl;

    @NotBlank
    private String metaInfoUrl;
  }
}
