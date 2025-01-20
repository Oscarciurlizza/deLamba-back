package com.app.deLamba.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class DatabaseProperties {
  private String host;
  private int port;
  private String database;
  private String username;
  private String password;
  private String authenticationDatabase;
  private String uri;
}
