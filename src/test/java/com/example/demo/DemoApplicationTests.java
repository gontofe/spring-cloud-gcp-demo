package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {"env_var_2=env_var_2_here",
		"env_var_3=sm@application-secret",
		"env_var_4=sm@application-fake",
		"env_var_5=sm@application-list"})
@ActiveProfiles("test")
class DemoApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	void contextLoads() {
		ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/config", String.class);
		assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
		String body = responseEntity.getBody();
		assertThat(body).contains("env_var_1_not_present");
		assertThat(body).contains("env_var_2_here");
		assertThat(body).contains("realValue");
		assertThat(body).contains("env_var_4_not_present");
		assertThat(body).contains("[\"five\",\"four\",\"three\",\"two\",\"one\"]");
	}
}
