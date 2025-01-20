package com.app.deLamba.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.app.deLamba.config.properties.DatabaseProperties;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMongoRepositories(basePackages = "com.app.deLamba")
@EnableMongoAuditing
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

  private final DatabaseProperties dbProperties;

  @Override
  protected String getDatabaseName() {
    return dbProperties.getDatabase();
  }

  @Override
  protected void configureClientSettings(MongoClientSettings.Builder builder) {
    // Configurar conexión basada en URI o propiedades individuales
    if (dbProperties.getUri() != null && !dbProperties.getUri().isEmpty()) {
      builder.applyConnectionString(new ConnectionString(dbProperties.getUri()));
    } else {
      String connectionString = String.format("mongodb://%s:%s@%s:%d/%s",
          dbProperties.getUsername(),
          dbProperties.getPassword(),
          dbProperties.getHost(),
          dbProperties.getPort(),
          dbProperties.getDatabase());
      builder.applyConnectionString(new ConnectionString(connectionString));
    }

    // Configurar pool de conexiones
    builder.applyToConnectionPoolSettings(pool -> pool
        .maxSize(20)
        .minSize(5)
        .maxWaitTime(2000, TimeUnit.MILLISECONDS));

    // Configurar timeouts
    builder.applyToSocketSettings(socket -> socket
        .connectTimeout(2000, TimeUnit.MILLISECONDS)
        .readTimeout(2000, TimeUnit.MILLISECONDS));
  }
}
