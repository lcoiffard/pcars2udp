package com.laurent.pcars2udp.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class LapRecord {

	@EmbeddedId
	private LapKey lapKey;

	@Column
	private String userPs4;

	@Column
	private String className;

	@Column
	private LocalTime recordLap;

	@Column
	private LocalDateTime recordDate;

	@Column
	LocalTime recordSectorOne;

	@Column
	LocalTime recordSectorTwo;

	@Column
	LocalTime recordSectorThree;

	public LocalTime getRecordLap() {
		return recordLap;
	}

	public void setRecordLap(LocalTime recordLap) {
		this.recordLap = recordLap;
	}

	public LapKey getLapKey() {
		return lapKey;
	}

	public void setLapKey(LapKey lapKey) {
		this.lapKey = lapKey;
	}

	public LocalDateTime getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(LocalDateTime recordDate) {
		this.recordDate = recordDate;
	}

	public String getUserPs4() {
		return userPs4;
	}

	public void setUserPs4(String userPs4) {
		this.userPs4 = userPs4;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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
