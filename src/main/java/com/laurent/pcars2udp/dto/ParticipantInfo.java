package com.laurent.pcars2udp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ParticipantInfo {

	public final static int LENGTH_BYTES_BUFFER = 1347;

	private Integer buildVersionNumber;
	private Integer packetType;
	private String carName;
	private String carClassName;
	private String trackLocation;
	private String trackVariation;
	private List<String> name;
	private List<Float> fastestLapTime;


}
