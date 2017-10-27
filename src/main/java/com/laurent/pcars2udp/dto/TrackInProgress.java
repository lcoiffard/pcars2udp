package com.laurent.pcars2udp.dto;

import org.springframework.stereotype.Component;

@Component
public class TrackInProgress {

	private Record recordSession = new Record();
	private Record recordCar = new Record();
	private Record recordClass = new Record();
	private Record recordTrack = new Record();

	public Record getRecordSession() {
		return recordSession;
	}

	public void setRecordSession(Record recordSession) {
		this.recordSession = recordSession;
	}

	public Record getRecordCar() {
		return recordCar;
	}

	public void setRecordCar(Record recordCar) {
		this.recordCar = recordCar;
	}

	public Record getRecordClass() {
		return recordClass;
	}

	public void setRecordClass(Record recordClass) {
		this.recordClass = recordClass;
	}

	public Record getRecordTrack() {
		return recordTrack;
	}

	public void setRecordTrack(Record recordTrack) {
		this.recordTrack = recordTrack;
	}

}
