package com.laurent.pcars2udp.dto;

import org.springframework.stereotype.Component;

@Component
public class TrackInProgress {

	private TimeLap currentLap = new TimeLap();
	private TimeLap recordSession = new TimeLap();
	private TimeLap recordCar = new TimeLap();
	private TimeLap recordClass = new TimeLap();
	private TimeLap recordTrack = new TimeLap();

	public TimeLap getRecordSession() {
		return recordSession;
	}

	public void setRecordSession(TimeLap recordSession) {
		this.recordSession = recordSession;
	}

	public TimeLap getRecordCar() {
		return recordCar;
	}

	public void setRecordCar(TimeLap recordCar) {
		this.recordCar = recordCar;
	}

	public TimeLap getRecordClass() {
		return recordClass;
	}

	public void setRecordClass(TimeLap recordClass) {
		this.recordClass = recordClass;
	}

	public TimeLap getRecordTrack() {
		return recordTrack;
	}

	public void setRecordTrack(TimeLap recordTrack) {
		this.recordTrack = recordTrack;
	}

	public TimeLap getCurrentLap() {
		return currentLap;
	}

	public void setCurrentLap(TimeLap currentLap) {
		this.currentLap = currentLap;
	}

}
