package com.laurent.pcars2udp.dto;

import java.time.Duration;
import java.time.LocalTime;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.stereotype.Component;

@Component
public class TrackInProgress {

	private String trackName;
	private String carName;
	private LocalTime recordSession;
	private LocalTime recordCar;
	private LocalTime recordClass;
	private LocalTime recordTrack;
	private String recordClassCar;
	private String recordTrackCar;

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public LocalTime getRecordSession() {
		return recordSession;
	}

	public void setRecordSession(LocalTime recordSession) {
		this.recordSession = recordSession;
	}

	public LocalTime getRecordCar() {
		return recordCar;
	}

	public void setRecordCar(LocalTime recordCar) {
		this.recordCar = recordCar;
	}

	public LocalTime getRecordClass() {
		return recordClass;
	}

	public void setRecordClass(LocalTime recordClass) {
		this.recordClass = recordClass;
	}

	public LocalTime getRecordTrack() {
		return recordTrack;
	}

	public void setRecordTrack(LocalTime recordTrack) {
		this.recordTrack = recordTrack;
	}

	public Boolean getRecordCarBeaten() {
		return isRecordBeaten(getRecordSession(), getRecordCar());
	}

	public String getDiffRecordCar() {

		return getStringDiff(getRecordSession(), getRecordCar());
	}

	public Boolean getRecordClassBeaten() {
		return isRecordBeaten(getRecordSession(), getRecordClass());
	}

	public String getDiffRecordClass() {

		return getStringDiff(getRecordSession(), getRecordClass());
	}

	public Boolean getRecordTrackBeaten() {
		return isRecordBeaten(getRecordSession(), getRecordTrack());
	}

	public String getDiffRecordTrack() {

		return getStringDiff(getRecordSession(), getRecordTrack());
	}

	private Boolean isRecordBeaten(LocalTime time, LocalTime recordToBeaten) {
		Duration diff = null;
		if (time != null && recordToBeaten != null) {
			diff = Duration.between(recordToBeaten, time);
		}
		return (time != null && recordToBeaten == null) || (diff != null && (diff.isNegative() || diff.isZero()));
	}

	private String getStringDiff(LocalTime time, LocalTime recordToBeaten) {
		String diffString = null;
		if (time != null && recordToBeaten != null) {
			Duration diff = Duration.between(recordToBeaten, time);
			diffString = (diff.isNegative() ? " - " : " + ")
					+ DurationFormatUtils.formatDuration(Math.abs(diff.toMillis()), "mm:ss.SSS");
		}
		return diffString;
	}

	public String getRecordClassCar() {
		return recordClassCar;
	}

	public void setRecordClassCar(String recordClassCar) {
		this.recordClassCar = recordClassCar;
	}

	public String getRecordTrackCar() {
		return recordTrackCar;
	}

	public void setRecordTrackCar(String recordTrackCar) {
		this.recordTrackCar = recordTrackCar;
	}

}
