package org.vectorsearch.config;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDbAtlasConfiguration extends AbstractMongoClientConfiguration {

    @Value("${mongodb.atlas.connection.string}")
    private String mongoConnectionString;

    @Value("${mongodb.atlas.database.name}")
    private String databaseName;

    @Bean
    @Nonnull
    public MongoDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(this.mongoConnectionString);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }

    @Override
    @Nonnull
    protected String getDatabaseName() {
        return this.databaseName;
    }
}
