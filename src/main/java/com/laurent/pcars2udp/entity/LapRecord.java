package com.laurent.pcars2udp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = { @Index(name = "IDX_RECORD_CLASS", columnList = "className,trackLocation,trackVariation"),
		@Index(name = "IDX_RECORD_TRACK", columnList = "trackLocation,trackVariation") })
public class LapRecord {

	@EmbeddedId
	private LapKey lapKey;

	@Column
	private String userPs4;

	@Column
	private String className;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime recordLap;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime recordDate;

	@Column(columnDefinition = "TIMESTAMP")
	LocalDateTime recordSectorOne;

	@Column(columnDefinition = "TIMESTAMP")
	LocalDateTime recordSectorTwo;

	@Column(columnDefinition = "TIMESTAMP")
	LocalDateTime recordSectorThree;

	public LocalDateTime getRecordLap() {
		return recordLap;
	}

	public void setRecordLap(LocalDateTime recordLap) {
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
