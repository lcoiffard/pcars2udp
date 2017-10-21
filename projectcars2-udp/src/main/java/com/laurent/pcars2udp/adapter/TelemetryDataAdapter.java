package com.laurent.pcars2udp.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laurent.pcars2udp.dto.TelemetryData;
import com.laurent.pcars2udp.util.BufferByteUtils;

@Component
public class TelemetryDataAdapter {
	@Autowired
	private BufferByteUtils bufferByteUtils;

	public TelemetryData getTelemetryData(TelemetryData telemetryData, byte[] data) {

		telemetryData.setBuildVersionNumber(bufferByteUtils.getInteger(data, 0, 16));
		telemetryData.setPacketType(bufferByteUtils.getInteger(data, 2, 8));
		telemetryData.setGameSessionState(bufferByteUtils.getInteger(data, 3, 8));
		telemetryData.setBestLapTime(bufferByteUtils.getFloat(data, 12, 32));
		telemetryData.setLastLapTime(bufferByteUtils.getFloat(data, 16, 32));
		telemetryData.setCurrentTime(bufferByteUtils.getFloat(data, 20, 32));
		telemetryData.setFastestSector1Time(bufferByteUtils.getFloat(data, 60, 32));
		telemetryData.setFastestSector2Time(bufferByteUtils.getFloat(data, 64, 32));
		telemetryData.setFastestSector3Time(bufferByteUtils.getFloat(data, 68, 32));

		return telemetryData;
	}
}
