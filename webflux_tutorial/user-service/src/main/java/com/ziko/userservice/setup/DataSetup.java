package com.ziko.userservice.setup;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 15 Nov, 2023
 */

@Component
@RequiredArgsConstructor
public class DataSetup implements CommandLineRunner {

    @Value("classpath:h2/init.sql")
    private Resource initSql;

    private final R2dbcEntityTemplate entityTemplate;

    @Override
    public void run(String... args) throws Exception {
        String query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(query);
        this.entityTemplate.getDatabaseClient()
                .sql(query)
                .then()
                .subscribe();

    }
}
