package com.laurent.pcars2udp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "LAP_RECORD")
public class LapRecord {

	@EmbeddedId
	private LapKey lapKey;

	@ManyToOne
	@MapsId("carId")
	private Car car;

	@ManyToOne
	@MapsId("trackId")
	private Track track;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime recordLap;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime recordDate;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime recordSectorOne;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime recordSectorTwo;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime recordSectorThree;

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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

}
