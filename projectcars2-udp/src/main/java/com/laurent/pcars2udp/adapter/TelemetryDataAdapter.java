package com.laurent.pcars2udp.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laurent.pcars2udp.dto.TelemetryData;
import com.laurent.pcars2udp.util.BufferByteUtils;
import com.laurent.pcars2udp.util.LogUtils;

@Component
public class TelemetryDataAdapter {
	@Autowired
	private BufferByteUtils bufferByteUtils;

	@Autowired
	private LogUtils logUtils;

	public TelemetryData getTelemetryData(TelemetryData telemetryData, byte[] data) {

		telemetryData.setBuildVersionNumber(bufferByteUtils.getInteger(data, 0, 16));
		telemetryData.setPacketType(bufferByteUtils.getInteger(data, 2, 8));
		telemetryData.setGameSessionState(bufferByteUtils.getInteger(data, 3, 8));
		telemetryData.setBestLapTime(bufferByteUtils.getFloat(data, 12, 32));
		telemetryData.setLastLapTime(bufferByteUtils.getFloat(data, 16, 32));
		telemetryData.setCurrentTime(bufferByteUtils.getFloat(data, 20, 32));

		return telemetryData;
	}
}
