package com.laurent.pcars2udp.dto;

import org.springframework.stereotype.Component;

@Component
public class TelemetryData {
	public final static int LENGTH_BYTES_BUFFER = 1367;

	private Integer buildVersionNumber;
	private Integer packetType;
	private Integer gameSessionState;
	private float bestLapTime;
	private float lastLapTime;
	private float currentTime;

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

	public float getBestLapTime() {
		return bestLapTime;
	}

	public void setBestLapTime(float bestLapTime) {
		this.bestLapTime = bestLapTime;
	}

	public float getLastLapTime() {
		return lastLapTime;
	}

	public void setLastLapTime(float lastLapTime) {
		this.lastLapTime = lastLapTime;
	}

	public float getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(float currentTime) {
		this.currentTime = currentTime;
	}

	public Integer getGameSessionState() {
		return gameSessionState;
	}

	public void setGameSessionState(Integer gameSessionState) {
		this.gameSessionState = gameSessionState;
	}

}
