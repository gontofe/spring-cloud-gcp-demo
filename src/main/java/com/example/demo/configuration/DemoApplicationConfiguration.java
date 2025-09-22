package com.example.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(value = "app.config")
public class DemoApplicationConfiguration {

    private String env1;

    private String env2;

    private String env3;

    private String env4;

    private List<String> env5;

    private String env6;

    private String env7;

    private String env8;

    private String env9;

    private List<String> env10;
}
