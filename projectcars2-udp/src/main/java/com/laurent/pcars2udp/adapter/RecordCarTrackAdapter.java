package com.laurent.pcars2udp.adapter;

import org.springframework.stereotype.Component;

import com.laurent.pcars2udp.dto.RecordCarTrack;
import com.laurent.pcars2udp.entity.LapKey;
import com.laurent.pcars2udp.entity.LapRecord;

@Component
public class RecordCarTrackAdapter {

	public RecordCarTrack convertToRecordCar(LapRecord lapRecord) {
		RecordCarTrack recordCarTrack = new RecordCarTrack();
		if (lapRecord.getLapKey() != null) {
			recordCarTrack.setTrackName(lapRecord.getLapKey().getTrackLocation());
			recordCarTrack.setTrackVariation(lapRecord.getLapKey().getTrackVariation());
			recordCarTrack.setCarName(lapRecord.getLapKey().getCarName());
		}
		recordCarTrack.setCarClass(lapRecord.getClassName());
		recordCarTrack.setUser(lapRecord.getUserPs4());
		recordCarTrack.setRecordLap(lapRecord.getRecordLap());
		recordCarTrack.setDateRecord(lapRecord.getRecordDate());
		return recordCarTrack;
	}

	public LapRecord convertToLapRecord(RecordCarTrack recordCarTrack) {
		LapRecord lapRecord = new LapRecord();
		lapRecord.setLapKey(new LapKey());
		lapRecord.getLapKey().setCarName(recordCarTrack.getCarName());
		lapRecord.getLapKey().setTrackLocation(recordCarTrack.getTrackName());
		lapRecord.getLapKey().setTrackVariation(recordCarTrack.getTrackVariation());
		lapRecord.setUserPs4(recordCarTrack.getUser());
		lapRecord.setClassName(recordCarTrack.getCarClass());
		lapRecord.setRecordDate(recordCarTrack.getDateRecord());
		lapRecord.setRecordLap(recordCarTrack.getRecordLap());
		return lapRecord;

	}
}
