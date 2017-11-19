package com.laurent.pcars2udp;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.laurent.pcars2udp.thread.UDPThread;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Application {

	@Autowired
	private UDPThread udpThread;

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(Application.class, args);

		Runtime.getRuntime().exec(new String[] { "sh", "-c", "/opt/google/chrome/chrome http://localhost:8080" });
	}

	@PostConstruct
	public void init() throws InterruptedException {
		udpThread.run();

	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.findAndRegisterModules();
		return mapper;
	}

}
