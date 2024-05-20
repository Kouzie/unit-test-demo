package com.demo.unit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
@ContextConfiguration(classes = MysqlTestContainer.TestDataSourceConfiguration.class)
public class MysqlTestContainer {


    static MySQLContainer MY_SQL_CONTAINER = new MySQLContainer("mysql:8") // MySQLContainer 객체 생성
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");

    @BeforeAll
    static void beforeAll() {
        MY_SQL_CONTAINER.start();
    }

    @AfterAll
    static void afterAll() {
        MY_SQL_CONTAINER.stop();
    }

    @TestConfiguration
    public static class TestDataSourceConfiguration {
        @Bean
        public DataSource dataSource() {
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceBuilder.url(MY_SQL_CONTAINER.getJdbcUrl());
            dataSourceBuilder.username(MY_SQL_CONTAINER.getUsername());
            dataSourceBuilder.password(MY_SQL_CONTAINER.getPassword());
            return dataSourceBuilder.build();
        }
    }
}
