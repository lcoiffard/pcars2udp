package com.laurent.pcars2udp.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laurent.pcars2udp.dto.ParticipantInfo;
import com.laurent.pcars2udp.dto.Record;
import com.laurent.pcars2udp.dto.TelemetryData;
import com.laurent.pcars2udp.dto.TrackInProgress;
import com.laurent.pcars2udp.entity.LapRecord;
import com.laurent.pcars2udp.repo.LapRecordRepo;

@Controller
public class Pcars2UDPController {

	@Autowired
	private ParticipantInfo participantInfo;

	@Autowired
	private TelemetryData telemetryData;

	@Autowired
	private LapRecordRepo lapRecordRepo;

	@Autowired
	private TrackInProgress trackInProgress;

	int i = 0;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		refreshTrackInProgress();
		model.addAttribute(trackInProgress);
		return "pcars2udp";
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public String refresh(Model model) {
		refreshTrackInProgress();
		// Util for mock

		// trackInProgress.getRecordSession().setTrackName("Aerordromo international
		// Algarte");
		// trackInProgress.getRecordSession().setTrackVariation("Long circuit");
		// trackInProgress.getRecordSession().setCarName("Mercedes R01 GT 5");
		// trackInProgress.getRecordSession().setClassName("Vintage");
		//
		// trackInProgress.getRecordSession().setRecord(LocalTime.of(0, 1, 55,
		// 76543210).minusSeconds(i++));
		//
		// trackInProgress.getRecordSession().setRecordSectorOne(LocalTime.of(0, 0, 19,
		// 876543210).minusSeconds(i));
		// trackInProgress.getRecordSession().setRecordSectorTwo(LocalTime.of(0, 1, 5,
		// 876543210).minusSeconds(i));
		// trackInProgress.getRecordSession().setRecordSectorThree(LocalTime.of(0, 0,
		// 37, 876543210).minusSeconds(i));
		//
		// trackInProgress.getRecordCar().setRecord(LocalTime.of(0, 1, 52, 876543210));
		// trackInProgress.getRecordCar().setRecordSectorOne(LocalTime.of(0, 0, 17,
		// 876543210));
		// trackInProgress.getRecordCar().setRecordSectorTwo(LocalTime.of(0, 1, 1,
		// 876543210));
		// trackInProgress.getRecordCar().setRecordSectorThree(LocalTime.of(0, 0, 34,
		// 876543210));
		// trackInProgress.getRecordClass().setRecord(LocalTime.of(0, 1, 51,
		// 776543210));
		// trackInProgress.getRecordTrack().setRecord(LocalTime.of(0, 1, 50,
		// 676543210));
		// trackInProgress.getRecordClass().setCarName("Mazda");
		// trackInProgress.getRecordTrack().setCarName("Mercedes");
		// trackInProgress.getRecordTrack().setClassName("GTO");

		model.addAttribute(trackInProgress);
		return "pcars2udp :: data";
	}

	public void refreshTrackInProgress() {

		if (telemetryData.getGameSessionState() == null || telemetryData.getGameSessionState() == 1
				|| StringUtils.isEmpty(participantInfo.getCarName())) {
			trackInProgress.getRecordSession().reset();
			trackInProgress.getRecordCar().reset();
			trackInProgress.getRecordClass().reset();
			trackInProgress.getRecordTrack().reset();

		} else {

			// get record first time
			if (StringUtils.isEmpty(trackInProgress.getRecordSession().getTrackName())
					&& StringUtils.isEmpty(trackInProgress.getRecordSession().getCarName())
					&& !StringUtils.isEmpty(participantInfo.getCarName())
					&& !StringUtils.isEmpty(participantInfo.getTrackLocation())) {
				refreshRecordCar();
				refreshRecordClass();
				refreshRecordTrack();
			}

			trackInProgress.getRecordSession().setTrackName(participantInfo.getTrackLocation());
			trackInProgress.getRecordSession().setTrackVariation(participantInfo.getTrackVariation());
			trackInProgress.getRecordSession().setCarName(participantInfo.getCarName());
			trackInProgress.getRecordSession().setClassName(participantInfo.getCarClassName());

			if (telemetryData.getBestLapTime() > 0) {
				LocalTime bestLapSession = LocalTime.ofNanoOfDay((long) (telemetryData.getBestLapTime() * 1000000000));

				// if best record session is beaten and it was the ancien record car, class,
				// track, replace
				if (trackInProgress.getRecordSession().isRecordBeaten(bestLapSession)) {
					if (trackInProgress.getRecordCar().isRecordBeaten(trackInProgress.getRecordSession().getRecord())) {
						trackInProgress.setRecordCar(trackInProgress.getRecordSession().clone());
					}
					if (trackInProgress.getRecordClass()
							.isRecordBeaten(trackInProgress.getRecordSession().getRecord())) {
						trackInProgress.setRecordClass(trackInProgress.getRecordSession().clone());
					}
					if (trackInProgress.getRecordTrack()
							.isRecordBeaten(trackInProgress.getRecordSession().getRecord())) {
						trackInProgress.setRecordTrack(trackInProgress.getRecordSession().clone());
					}
				}

				trackInProgress.getRecordSession().setRecord(bestLapSession);
			} else {
				// Refresh record if restart race
				if (trackInProgress.getRecordSession() != null) {
					refreshRecordCar();
					refreshRecordClass();
					refreshRecordTrack();
				}
				trackInProgress.getRecordSession().resetTime();
			}

		}

	}

	private void refreshRecordCar() {
		LapRecord lapRecordCar = lapRecordRepo.findByLapKey_CarNameAndLapKey_TrackLocationAndLapKey_TrackVariation(
				participantInfo.getCarName(), participantInfo.getTrackLocation(), participantInfo.getTrackVariation());
		if (lapRecordCar != null) {
			refreshRecord(trackInProgress.getRecordCar(), lapRecordCar);
		}

	}

	private void refreshRecord(Record record, LapRecord lapRecord) {
		record.setTrackName(lapRecord.getLapKey().getTrackLocation());
		record.setTrackVariation(lapRecord.getLapKey().getTrackVariation());
		record.setCarName(lapRecord.getLapKey().getCarName());
		record.setClassName(lapRecord.getClassName());
		record.setRecord(lapRecord.getRecordLap());
		record.setRecordSectorOne(lapRecord.getRecordSectorOne());
		record.setRecordSectorTwo(lapRecord.getRecordSectorTwo());
		record.setRecordSectorThree(lapRecord.getRecordSectorThree());

	}

	private void refreshRecordClass() {
		LapRecord lapRecordClass = lapRecordRepo
				.findFirstByClassNameAndLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(
						participantInfo.getCarClassName(), participantInfo.getTrackLocation(),
						participantInfo.getTrackVariation());
		if (lapRecordClass != null) {
			refreshRecord(trackInProgress.getRecordClass(), lapRecordClass);
		}
	}

	private void refreshRecordTrack() {
		LapRecord lapRecordTrack = lapRecordRepo
				.findFirstByLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(
						participantInfo.getTrackLocation(), participantInfo.getTrackVariation());
		if (lapRecordTrack != null) {
			refreshRecord(trackInProgress.getRecordTrack(), lapRecordTrack);
		}
	}
}
