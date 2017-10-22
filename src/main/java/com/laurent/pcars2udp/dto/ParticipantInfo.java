package com.laurent.pcars2udp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ParticipantInfo {

	public final static int LENGTH_BYTES_BUFFER = 1347;

	private Integer buildVersionNumber;
	private Integer packetType;
	private String carName;
	private String carClassName;
	private String trackLocation;
	private String trackVariation;
	private List<String> name;
	private List<Float> fastestLapTime;

	public Integer getBuildVersionNumber() {
		return buildVersionNumber;
	}

	public void setBuildVersionNumber(Integer buildVersionNumber) {
		this.buildVersionNumber = buildVersionNumber;
	}

	public Integer getPacketType() {
		return packetType;
	}

	public void setPacketType(Integer packetType) {
		this.packetType = packetType;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarClassName() {
		return carClassName;
	}

	public void setCarClassName(String carClassName) {
		this.carClassName = carClassName;
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

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<Float> getFastestLapTime() {
		return fastestLapTime;
	}

	public void setFastestLapTime(List<Float> fastestLapTime) {
		this.fastestLapTime = fastestLapTime;
	}

}
