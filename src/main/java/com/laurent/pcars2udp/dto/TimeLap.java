package com.laurent.pcars2udp.dto;

import java.time.Duration;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class TimeLap implements Cloneable {

	private String trackName;
	private String trackVariation;
	private String carName;
	private String className;

	private LocalTime timeLap;
	private LocalTime timeSectorOne;
	private LocalTime timeSectorTwo;
	private LocalTime timeSectorThree;

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

	public LocalTime getTimeLap() {
		return timeLap;
	}

	public void setTimeLap(LocalTime timeLap) {
		this.timeLap = timeLap;
	}

	public LocalTime getTimeSectorOne() {
		return timeSectorOne;
	}

	public void setTimeSectorOne(LocalTime timeSectorOne) {
		this.timeSectorOne = timeSectorOne;
	}

	public LocalTime getTimeSectorTwo() {
		return timeSectorTwo;
	}

	public void setTimeSectorTwo(LocalTime timeSectorTwo) {
		this.timeSectorTwo = timeSectorTwo;
	}

	public LocalTime getTimeSectorThree() {
		return timeSectorThree;
	}

	public void setTimeSectorThree(LocalTime timeSectorThree) {
		this.timeSectorThree = timeSectorThree;
	}

	public Boolean isTimeBeaten(LocalTime time) {
		return isBeaten(time, this.timeLap);
	}

	public String getDiffTime(LocalTime time) {
		return getStringDiff(time, this.timeLap);
	}

	public Boolean isSectorOneBeaten(LocalTime time) {
		return isBeaten(time, this.timeSectorOne);
	}

	public String getDiffSectorOne(LocalTime time) {
		return getStringDiff(time, this.timeSectorOne);
	}

	public Boolean isSectorTwoBeaten(LocalTime time) {
		return isBeaten(time, this.timeSectorTwo);
	}

	public String getDiffSectorTwo(LocalTime time) {
		return getStringDiff(time, this.timeSectorTwo);
	}

	public Boolean isSectorThreeBeaten(LocalTime time) {
		return isBeaten(time, this.timeSectorThree);
	}

	public String getDiffSectorThree(LocalTime time) {
		return getStringDiff(time, this.timeSectorThree);
	}

	public Boolean isBeaten(LocalTime time, LocalTime timeToBeat) {
		return (time != null && timeToBeat == null)
				|| (time != null && timeToBeat != null && time.isBefore(timeToBeat));
	}

	public String getStringDiff(LocalTime time, LocalTime timeToBeat) {
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

}
