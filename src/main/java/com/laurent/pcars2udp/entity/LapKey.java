package com.laurent.pcars2udp.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LapKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3552165560287566924L;

	private String carName;

	private String trackLocation;

	private String trackVariation;

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getTrackLocation() {
		return trackLocation;
	}

	public void setTrackLocation(String trackLocation) {
		this.trackLocation = trackLocation;
	}

	public String getTrackVariation() {
		return trackVariation;
	}

	public void setTrackVariation(String trackVariation) {
		this.trackVariation = trackVariation;
	}
}
