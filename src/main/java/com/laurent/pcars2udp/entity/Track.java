package com.laurent.pcars2udp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "TRACK", indexes = {
		@Index(name = "IDX_TRACKLOCATIONVARIATION", columnList = "trackLocation,trackVariation", unique = true) })
public class Track {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String trackLocation;
	@Column(nullable = true)
	private String trackVariation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
