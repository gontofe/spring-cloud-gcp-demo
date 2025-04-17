package com.example.demo.controller;

import com.example.demo.configuration.DemoApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoApplicationController {

    private final DemoApplicationConfiguration demoApplicationConfiguration;

    public DemoApplicationController(DemoApplicationConfiguration demoApplicationConfiguration) {
        this.demoApplicationConfiguration = demoApplicationConfiguration;
    }

    @GetMapping(value = "/config")
    public ResponseEntity<List<Object>> getConfig() {
        return ResponseEntity.ok(List.of(demoApplicationConfiguration.getEnv1(),
                demoApplicationConfiguration.getEnv2(),
                demoApplicationConfiguration.getEnv3(),
                demoApplicationConfiguration.getEnv4(),
                demoApplicationConfiguration.getEnv5()));
    }
}
