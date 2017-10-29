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
	private float fastestSector1Time;
	private float fastestSector2Time;
	private float fastestSector3Time;
	private float currentSector1Time;
	private float currentSector2Time;
	private float currentSector3Time;

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

	public float getFastestSector1Time() {
		return fastestSector1Time;
	}

	public void setFastestSector1Time(float fastestSector1Time) {
		this.fastestSector1Time = fastestSector1Time;
	}

	public float getFastestSector2Time() {
		return fastestSector2Time;
	}

	public void setFastestSector2Time(float fastestSector2Time) {
		this.fastestSector2Time = fastestSector2Time;
	}

	public float getFastestSector3Time() {
		return fastestSector3Time;
	}

	public void setFastestSector3Time(float fastestSector3Time) {
		this.fastestSector3Time = fastestSector3Time;
	}

	public float getCurrentSector1Time() {
		return currentSector1Time;
	}

	public void setCurrentSector1Time(float currentSector1Time) {
		this.currentSector1Time = currentSector1Time;
	}

	public float getCurrentSector2Time() {
		return currentSector2Time;
	}

	public void setCurrentSector2Time(float currentSector2Time) {
		this.currentSector2Time = currentSector2Time;
	}

	public float getCurrentSector3Time() {
		return currentSector3Time;
	}

	public void setCurrentSector3Time(float currentSector3Time) {
		this.currentSector3Time = currentSector3Time;
	}

}
