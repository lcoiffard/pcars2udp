package com.laurent.pcars2udp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laurent.pcars2udp.entity.LapKey;
import com.laurent.pcars2udp.entity.LapRecord;

public interface LapRecordRepo extends JpaRepository<LapRecord, LapKey> {

	public LapRecord findByCar_CarNameAndTrack_TrackLocationAndTrack_TrackVariation(String carName,
			String trackLocation, String trackVariation);

	public LapRecord findFirstByCar_ClassNameAndTrack_TrackLocationAndTrack_TrackVariationOrderByRecordLapAsc(
			String carClass, String trackLocation, String trackVariation);

	public LapRecord findFirstByTrack_TrackLocationAndTrack_TrackVariationOrderByRecordLapAsc(String trackLocation,
			String trackVariation);

}
