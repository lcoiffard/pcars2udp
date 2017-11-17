package com.laurent.pcars2udp.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class RecordCarTrack {
	private String user;
	private String trackName;
	private String trackVariation;
	private String carName;
	private String carClass;
	private LocalDateTime recordLap;
	private LocalDateTime recordSectorOne;
	private LocalDateTime recordSectorTwo;
	private LocalDateTime recordSectorThree;
	private LocalDateTime dateRecord;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getTrackVariation() {
		return trackVariation;
	}

	public void setTrackVariation(String trackVariation) {
		this.trackVariation = trackVariation;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public LocalDateTime getRecordLap() {
		return recordLap;
	}

	public void setRecordLap(LocalDateTime recordLap) {
		this.recordLap = recordLap;
	}

	public LocalDateTime getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(LocalDateTime dateRecord) {
		this.dateRecord = dateRecord;
	}

	public LocalDateTime getRecordSectorOne() {
		return recordSectorOne;
	}

	public void setRecordSectorOne(LocalDateTime recordSectorOne) {
		this.recordSectorOne = recordSectorOne;
	}

	public LocalDateTime getRecordSectorTwo() {
		return recordSectorTwo;
	}

	public void setRecordSectorTwo(LocalDateTime recordSectorTwo) {
		this.recordSectorTwo = recordSectorTwo;
	}

	public LocalDateTime getRecordSectorThree() {
		return recordSectorThree;
	}

	public void setRecordSectorThree(LocalDateTime recordSectorThree) {
		this.recordSectorThree = recordSectorThree;
	}
}
