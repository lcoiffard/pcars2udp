package com.laurent.pcars2udp.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TelemetryData {
	public final static int LENGTH_BYTES_BUFFER = 1367;

	private Integer buildVersionNumber;
	private Integer packetType;
	private Integer gameSessionState;
	private float bestLapTime;
	private float lastLapTime;
	private float currentTime;
	private float fastestSector1Time;
	private float fastestSector2Time;
	private float fastestSector3Time;
	private float currentSector1Time;
	private float currentSector2Time;
	private float currentSector3Time;


}
