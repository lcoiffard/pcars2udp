package com.laurent.pcars2udp.thread;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.laurent.pcars2udp.adapter.RecordCarTrackAdapter;
import com.laurent.pcars2udp.dto.ParticipantInfo;
import com.laurent.pcars2udp.dto.RecordCarTrack;
import com.laurent.pcars2udp.dto.TelemetryData;
import com.laurent.pcars2udp.entity.LapRecord;
import com.laurent.pcars2udp.repo.LapRecordRepo;
import com.laurent.pcars2udp.util.LogUtils;

@Component
public class SaveBestLapThread {

	@Autowired
	private ParticipantInfo participantInfo;

	@Autowired
	private TelemetryData telemetryData;

	@Autowired
	private LapRecordRepo lapRecordRepo;

	@Autowired
	private RecordCarTrack recordCarTrack;

	@Autowired
	private RecordCarTrackAdapter recordCarTrackAdapter;

	@Autowired
	private LogUtils logUtils;

	@Scheduled(fixedDelay = 1000)
	public void saveBestLap() {

		// if not at the menu page
		if (telemetryData.getGameSessionState() != null && telemetryData.getGameSessionState() > 1) {
			// if a best lap is done
			if (!StringUtils.isEmpty(participantInfo.getCarName()) && telemetryData.getBestLapTime() > 0) {

				LocalDateTime bestLapSession = LocalDateTime.of(LocalDate.ofEpochDay(0),
						LocalTime.ofNanoOfDay((long) (telemetryData.getBestLapTime() * 1000000000)));

				if (StringUtils.isEmpty(recordCarTrack.getCarName())) {

					LapRecord lapRecord = lapRecordRepo.findByCar_CarNameAndTrack_TrackLocationAndTrack_TrackVariation(
							participantInfo.getCarName(), participantInfo.getTrackLocation(),
							participantInfo.getTrackVariation());
					if (lapRecord != null) {
						recordCarTrack = recordCarTrackAdapter.convertToRecordCar(lapRecord);
					} else {
						recordCarTrack.setCarName(participantInfo.getCarName());
						recordCarTrack.setTrackName(participantInfo.getTrackLocation());
						recordCarTrack.setTrackVariation(participantInfo.getTrackVariation());
					}
				}
				if (recordCarTrack.getRecordLap() == null || recordCarTrack.getRecordLap().isAfter(bestLapSession)) {

					recordCarTrack.setCarClass(participantInfo.getCarClassName());
					recordCarTrack.setDateRecord(LocalDateTime.now());
					recordCarTrack.setRecordLap(bestLapSession);
					LocalDateTime recordSectorOne = LocalDateTime.of(LocalDate.ofEpochDay(0),
							LocalTime.ofNanoOfDay((long) (telemetryData.getFastestSector1Time() * 1000000000)));
					LocalDateTime recordSectorTwo = LocalDateTime.of(LocalDate.ofEpochDay(0),
							LocalTime.ofNanoOfDay((long) (telemetryData.getFastestSector2Time() * 1000000000)));
					LocalDateTime recordSectorThree = LocalDateTime.of(LocalDate.ofEpochDay(0),
							LocalTime.ofNanoOfDay((long) (telemetryData.getFastestSector3Time() * 1000000000)));
					recordCarTrack.setRecordSectorOne(recordSectorOne);
					recordCarTrack.setRecordSectorTwo(recordSectorTwo);
					recordCarTrack.setRecordSectorThree(recordSectorThree);

					lapRecordRepo.save(recordCarTrackAdapter.convertToLapRecord(recordCarTrack));
					logUtils.println("New record save : " + recordCarTrack.getRecordLap());
				}

			}
		} else {
			recordCarTrack.setCarName(null);
			recordCarTrack.setTrackName(null);
			recordCarTrack.setTrackVariation(null);
			recordCarTrack.setCarClass(null);
			recordCarTrack.setDateRecord(null);
			recordCarTrack.setRecordLap(null);
			recordCarTrack.setRecordSectorOne(null);
			recordCarTrack.setRecordSectorTwo(null);
			recordCarTrack.setRecordSectorThree(null);
		}

	}

}