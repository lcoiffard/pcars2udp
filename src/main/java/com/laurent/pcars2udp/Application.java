package com.laurent.pcars2udp;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

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

}
