package com.laurent.pcars2udp.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laurent.pcars2udp.dto.RecordCarTrack;
import com.laurent.pcars2udp.entity.Car;
import com.laurent.pcars2udp.entity.LapKey;
import com.laurent.pcars2udp.entity.LapRecord;
import com.laurent.pcars2udp.entity.Track;
import com.laurent.pcars2udp.repo.CarRepo;
import com.laurent.pcars2udp.repo.TrackRepo;
import com.laurent.pcars2udp.util.LogUtils;

@Component
public class RecordCarTrackAdapter {

	@Autowired
	private TrackRepo trackRepo;

	@Autowired
	private CarRepo carRepo;

	@Autowired
	private LogUtils logUtils;

	public RecordCarTrack convertToRecordCar(LapRecord lapRecord) {
		RecordCarTrack recordCarTrack = new RecordCarTrack();
		recordCarTrack.setTrackName(lapRecord.getTrack().getTrackLocation());
		recordCarTrack.setTrackVariation(lapRecord.getTrack().getTrackVariation());
		recordCarTrack.setCarName(lapRecord.getCar().getCarName());
		recordCarTrack.setCarClass(lapRecord.getCar().getClassName());

		recordCarTrack.setRecordLap(lapRecord.getRecordLap());
		recordCarTrack.setDateRecord(lapRecord.getRecordDate());
		recordCarTrack.setRecordSectorOne(lapRecord.getRecordSectorOne());
		recordCarTrack.setRecordSectorTwo(lapRecord.getRecordSectorTwo());
		recordCarTrack.setRecordSectorThree(lapRecord.getRecordSectorThree());
		return recordCarTrack;
	}

	public LapRecord convertToLapRecord(RecordCarTrack recordCarTrack) {
		LapRecord lapRecord = new LapRecord();
		lapRecord.setLapKey(new LapKey());

		Track track = trackRepo.findByTrackLocationAndTrackVariation(recordCarTrack.getTrackName(),
				recordCarTrack.getTrackVariation());
		if (track == null) {
			track = new Track();
			track.setTrackLocation(recordCarTrack.getTrackName());
			track.setTrackVariation(recordCarTrack.getTrackVariation());
			track = trackRepo.save(track);
			logUtils.println(
					"New track save : " + recordCarTrack.getTrackName() + " / " + recordCarTrack.getTrackVariation());
		}

		Car car = carRepo.findByCarName(recordCarTrack.getCarName());
		if (car == null) {
			car = new Car();
			car.setCarName(recordCarTrack.getCarName());
			car.setClassName(recordCarTrack.getCarClass());
			car = carRepo.save(car);
			logUtils.println("New car save : " + recordCarTrack.getCarName() + " / " + recordCarTrack.getCarClass());
		}

		lapRecord.setCar(car);
		lapRecord.setTrack(track);
		lapRecord.getLapKey().setCarId(car.getId());
		lapRecord.getLapKey().setTrackId(track.getId());
		lapRecord.setRecordDate(recordCarTrack.getDateRecord());
		lapRecord.setRecordLap(recordCarTrack.getRecordLap());
		lapRecord.setRecordSectorOne(recordCarTrack.getRecordSectorOne());
		lapRecord.setRecordSectorTwo(recordCarTrack.getRecordSectorTwo());
		lapRecord.setRecordSectorThree(recordCarTrack.getRecordSectorThree());
		return lapRecord;

	}
}
