package com.laurent.pcars2udp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laurent.pcars2udp.entity.LapKey;
import com.laurent.pcars2udp.entity.LapRecord;

public interface LapRecordRepo extends JpaRepository<LapRecord, LapKey> {

	public LapRecord findByLapKey_CarNameAndLapKey_TrackLocationAndLapKey_TrackVariation(String carName,
			String trackLocation, String trackVariation);

	public LapRecord findFirstByClassNameAndLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(
			String carClass, String trackLocation, String trackVariation);

	public LapRecord findFirstByLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(String trackLocation,
			String trackVariation);
}
