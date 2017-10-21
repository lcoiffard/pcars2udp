package com.laurent.pcars2udp.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

@Component
public class RecordCarTrack {
	private String user;
	private String trackName;
	private String trackVariation;
	private String carName;
	private String carClass;
	private LocalTime recordLap;
	private LocalTime recordSectorOne;
	private LocalTime recordSectorTwo;
	private LocalTime recordSectorThree;
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

	public LocalTime getRecordLap() {
		return recordLap;
	}

	public void setRecordLap(LocalTime recordLap) {
		this.recordLap = recordLap;
	}

	public LocalDateTime getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(LocalDateTime dateRecord) {
		this.dateRecord = dateRecord;
	}

	public LocalTime getRecordSectorOne() {
		return recordSectorOne;
	}

	public void setRecordSectorOne(LocalTime recordSectorOne) {
		this.recordSectorOne = recordSectorOne;
	}

	public LocalTime getRecordSectorTwo() {
		return recordSectorTwo;
	}

	public void setRecordSectorTwo(LocalTime recordSectorTwo) {
		this.recordSectorTwo = recordSectorTwo;
	}

	public LocalTime getRecordSectorThree() {
		return recordSectorThree;
	}

	public void setRecordSectorThree(LocalTime recordSectorThree) {
		this.recordSectorThree = recordSectorThree;
	}
}
