package com.laurent.pcars2udp;

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

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@PostConstruct
	public void init() throws InterruptedException {
		udpThread.run();

	}

}
