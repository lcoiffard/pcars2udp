package com.laurent.pcars2udp.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TrackInProgress {

	private TimeLap currentLap = new TimeLap();
	private TimeLap recordSession = new TimeLap();
	private TimeLap recordCar = new TimeLap();
	private TimeLap recordClass = new TimeLap();
	private TimeLap recordTrack = new TimeLap();



}
