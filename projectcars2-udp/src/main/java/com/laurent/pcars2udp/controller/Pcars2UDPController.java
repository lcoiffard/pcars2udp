package com.laurent.pcars2udp.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.laurent.pcars2udp.dto.ParticipantInfo;
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
		return "starter";
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public String refresh(Model model) {
		refreshTrackInProgress();
		model.addAttribute(trackInProgress);
		return "starter :: data";
	}

	public void refreshTrackInProgress() {

		if (telemetryData.getGameSessionState() == null || telemetryData.getGameSessionState() == 1) {
			trackInProgress.setTrackName(null);
			trackInProgress.setCarName(null);
			trackInProgress.setRecordSession(null);
			trackInProgress.setRecordCar(null);
			trackInProgress.setRecordClass(null);
			trackInProgress.setRecordTrack(null);
		} else {
			trackInProgress.setTrackName(participantInfo.getTrackLocation());
			if (!StringUtils.isEmpty(participantInfo.getTrackVariation())) {
				trackInProgress
						.setTrackName(trackInProgress.getTrackName() + " / " + participantInfo.getTrackVariation());
			}
			trackInProgress.setCarName(participantInfo.getCarName());
			if (!StringUtils.isEmpty(participantInfo.getCarClassName())) {
				trackInProgress.setCarName(trackInProgress.getCarName() + " / " + participantInfo.getCarClassName());
			}

			if (!StringUtils.isEmpty(trackInProgress.getCarName())) {

				if (telemetryData.getBestLapTime() > 0) {
					LocalTime bestLapSession = LocalTime
							.ofNanoOfDay((long) (telemetryData.getBestLapTime() * 1000000000));
					trackInProgress.setRecordSession(bestLapSession);
				} else {
					trackInProgress.setRecordSession(null);
				}

				if (trackInProgress.getRecordCar() == null) {
					LapRecord lapRecordCar = lapRecordRepo
							.findByLapKey_CarNameAndLapKey_TrackLocationAndLapKey_TrackVariation(
									participantInfo.getCarName(), participantInfo.getTrackLocation(),
									participantInfo.getTrackVariation());
					if (lapRecordCar != null) {
						trackInProgress.setRecordCar(lapRecordCar.getRecordLap());
					}
				}

				if (trackInProgress.getRecordClass() == null) {
					LapRecord lapRecordClass = lapRecordRepo
							.findFirstByClassNameAndLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(
									participantInfo.getCarClassName(), participantInfo.getTrackLocation(),
									participantInfo.getTrackVariation());
					if (lapRecordClass != null) {
						trackInProgress.setRecordClass(lapRecordClass.getRecordLap());
					}
				}

				if (trackInProgress.getRecordTrack() == null) {
					LapRecord lapRecordTrack = lapRecordRepo
							.findFirstByLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(
									participantInfo.getTrackLocation(), participantInfo.getTrackVariation());
					if (lapRecordTrack != null) {
						trackInProgress.setRecordTrack(lapRecordTrack.getRecordLap());
					}
				}

			} else {
				trackInProgress.setRecordSession(null);
				trackInProgress.setRecordCar(null);
				trackInProgress.setRecordClass(null);
				trackInProgress.setRecordTrack(null);
			}

		}

	}
}
