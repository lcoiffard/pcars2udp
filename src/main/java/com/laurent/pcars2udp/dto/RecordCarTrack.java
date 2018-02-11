package com.laurent.pcars2udp.dto;

import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RecordCarTrack {
	private String trackName;
	private String trackVariation;
	private String carName;
	private String carClass;
	private LocalDateTime recordLap;
	private LocalDateTime recordSectorOne;
	private LocalDateTime recordSectorTwo;
	private LocalDateTime recordSectorThree;
	private LocalDateTime dateRecord;


}
