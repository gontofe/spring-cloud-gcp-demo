package com.example.demo;

import com.google.cloud.spring.secretmanager.SecretManagerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties = {"env_var_2=env_var_2_here",
		"env_var_3=sm@application-secret",
		"env_var_4=sm@application-fake",
		"env_var_5=sm@application-list",
        "env_var_7=env_var_7_here",
        "env_var_8=pm@test-c7038/global/application-secret/1",
        "env_var_9=pm@test-c7038/global/application-fake/1",
        "env_var_10=pm@test-c7038/global/application-list/1"})
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
        assertThat(body).contains("env_var_6_not_present");
        assertThat(body).contains("env_var_7_here");
        assertThat(body).contains("realValue");
        assertThat(body).contains("env_var_9_not_present");
        assertThat(body).contains("[\"five\",\"four\",\"three\",\"two\",\"one\"]");
	}
}
