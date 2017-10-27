package com.laurent.pcars2udp.dto;

import java.time.Duration;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class Record implements Cloneable {

	private String trackName;
	private String trackVariation;
	private String carName;
	private String className;

	private LocalTime record;
	private LocalTime recordSectorOne;
	private LocalTime recordSectorTwo;
	private LocalTime recordSectorThree;

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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public LocalTime getRecord() {
		return record;
	}

	public void setRecord(LocalTime record) {
		this.record = record;
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

	public Boolean isRecordBeaten(LocalTime time) {
		return isBeaten(time, this.record);
	}

	public String getDiffRecord(LocalTime time) {
		return getStringDiff(time, this.record);
	}

	public Boolean isSectorOneBeaten(LocalTime time) {
		return isBeaten(time, this.recordSectorOne);
	}

	public String getDiffSectorOne(LocalTime time) {
		return getStringDiff(time, this.recordSectorOne);
	}

	public Boolean isSectorTwoBeaten(LocalTime time) {
		return isBeaten(time, this.recordSectorTwo);
	}

	public String getDiffSectorTwo(LocalTime time) {
		return getStringDiff(time, this.recordSectorTwo);
	}

	public Boolean isSectorThreeBeaten(LocalTime time) {
		return isBeaten(time, this.recordSectorThree);
	}

	public String getDiffSectorThree(LocalTime time) {
		return getStringDiff(time, this.recordSectorThree);
	}

	public Boolean isBeaten(LocalTime time, LocalTime recordToBeat) {
		return (time != null && recordToBeat == null)
				|| (time != null && recordToBeat != null && time.isBefore(recordToBeat));
	}

	public String getStringDiff(LocalTime time, LocalTime recordToBeat) {
		String diffString = null;
		if (time != null && recordToBeat != null) {
			Duration diff = Duration.between(recordToBeat, time);
			diffString = (diff.isNegative() ? "-" : "+")
					+ DurationFormatUtils.formatDuration(Math.abs(diff.toMillis()), "mm:ss.SSS");
		}
		return diffString;
	}

	public String getLabelCar() {
		String label = null;
		if (!StringUtils.isEmpty(this.getCarName())) {
			label = this.getCarName();
		}
		if (!StringUtils.isEmpty(this.getClassName())) {
			label = label + " / " + this.getClassName();
		}
		return label;
	}

	public String getLabelTrack() {
		String label = null;
		if (!StringUtils.isEmpty(this.getTrackName())) {
			label = this.getTrackName();
		}
		if (!StringUtils.isEmpty(this.getTrackVariation())) {
			label = label + " / " + this.getTrackVariation();
		}
		return label;
	}

	public void reset() {
		this.trackName = null;
		this.trackVariation = null;
		this.carName = null;
		this.className = null;
		this.record = null;
		this.recordSectorOne = null;
		this.recordSectorTwo = null;
		this.recordSectorThree = null;
	}

	public void resetTime() {
		this.record = null;
		this.recordSectorOne = null;
		this.recordSectorTwo = null;
		this.recordSectorThree = null;
	}

	@Override
	public Record clone() {
		final Record clone;
		try {
			clone = (Record) super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException("superclass messed up", ex);
		}

		return clone;
	}

}
