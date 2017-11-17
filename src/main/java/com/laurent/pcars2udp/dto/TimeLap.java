package com.laurent.pcars2udp.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class TimeLap implements Cloneable {

	private String trackName;
	private String trackVariation;
	private String carName;
	private String className;

	private LocalDateTime timeLap;
	private LocalDateTime timeSectorOne;
	private LocalDateTime timeSectorTwo;
	private LocalDateTime timeSectorThree;

	private LocalDateTime dateRecord;

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

	public LocalDateTime getTimeLap() {
		return timeLap;
	}

	public void setTimeLap(LocalDateTime timeLap) {
		this.timeLap = timeLap;
	}

	public LocalDateTime getTimeSectorOne() {
		return timeSectorOne;
	}

	public void setTimeSectorOne(LocalDateTime timeSectorOne) {
		this.timeSectorOne = timeSectorOne;
	}

	public LocalDateTime getTimeSectorTwo() {
		return timeSectorTwo;
	}

	public void setTimeSectorTwo(LocalDateTime timeSectorTwo) {
		this.timeSectorTwo = timeSectorTwo;
	}

	public LocalDateTime getTimeSectorThree() {
		return timeSectorThree;
	}

	public void setTimeSectorThree(LocalDateTime timeSectorThree) {
		this.timeSectorThree = timeSectorThree;
	}

	public Boolean isTimeBeaten(LocalDateTime time) {
		return isBeaten(time, this.timeLap);
	}

	public String getDiffTime(LocalDateTime time) {
		return getStringDiff(time, this.timeLap);
	}

	public Boolean isSectorOneBeaten(LocalDateTime time) {
		return isBeaten(time, this.timeSectorOne);
	}

	public String getDiffSectorOne(LocalDateTime time) {
		return getStringDiff(time, this.timeSectorOne);
	}

	public Boolean isSectorTwoBeaten(LocalDateTime time) {
		return isBeaten(time, this.timeSectorTwo);
	}

	public String getDiffSectorTwo(LocalDateTime time) {
		return getStringDiff(time, this.timeSectorTwo);
	}

	public Boolean isSectorThreeBeaten(LocalDateTime time) {
		return isBeaten(time, this.timeSectorThree);
	}

	public String getDiffSectorThree(LocalDateTime time) {
		return getStringDiff(time, this.timeSectorThree);
	}

	public Boolean isBeaten(LocalDateTime time, LocalDateTime timeToBeat) {
		return (time != null && timeToBeat == null)
				|| (time != null && timeToBeat != null && time.isBefore(timeToBeat));
	}

	public String getStringDiff(LocalDateTime time, LocalDateTime timeToBeat) {
		String diffString = null;
		if (time != null && timeToBeat != null) {
			Duration diff = Duration.between(timeToBeat, time);
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
		this.timeLap = null;
		this.timeSectorOne = null;
		this.timeSectorTwo = null;
		this.timeSectorThree = null;
		this.dateRecord = null;
	}

	public void resetTime() {
		this.timeLap = null;
		this.timeSectorOne = null;
		this.timeSectorTwo = null;
		this.timeSectorThree = null;
	}

	@Override
	public TimeLap clone() {
		final TimeLap clone;
		try {
			clone = (TimeLap) super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException("superclass messed up", ex);
		}

		return clone;
	}

	public LocalDateTime getDateRecord() {
		return dateRecord;
	}

	public void setDateRecord(LocalDateTime dateRecord) {
		this.dateRecord = dateRecord;
	}

}
