package com.laurent.pcars2udp.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laurent.pcars2udp.dto.ParticipantInfo;
import com.laurent.pcars2udp.util.BufferByteUtils;

@Component
public class ParticipantInfoAdapter {
	@Autowired
	private BufferByteUtils bufferByteUtils;

	public ParticipantInfo getParticipantInfo(ParticipantInfo participantInfo, byte[] data) {
		participantInfo.setBuildVersionNumber(bufferByteUtils.getInteger(data, 0, 16));
		participantInfo.setPacketType(bufferByteUtils.getInteger(data, 2, 8));
		participantInfo.setCarName(bufferByteUtils.getString(data, 3, 64));
		participantInfo.setCarClassName(bufferByteUtils.getString(data, 67, 64));
		participantInfo.setTrackLocation(bufferByteUtils.getString(data, 131, 64));
		participantInfo.setTrackVariation(bufferByteUtils.getString(data, 195, 64));
		participantInfo.setName(bufferByteUtils.getListString(data, 259, 16, 64));
		participantInfo.setFastestLapTime(bufferByteUtils.getListFloat(data, 1283, 16, 32));

		return participantInfo;
	}
}
