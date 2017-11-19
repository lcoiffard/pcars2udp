package com.laurent.pcars2udp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LapKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3552165560287566924L;

	@Column
	private Long carId;
	@Column
	private Long trackId;

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

}
