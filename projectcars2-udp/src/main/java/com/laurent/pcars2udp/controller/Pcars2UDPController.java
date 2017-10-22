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
		// Util for mock

		/*
		 * trackInProgress.setCarName("test car");
		 * trackInProgress.setTrackName("test track");
		 * trackInProgress.setRecordSession(LocalTime.of(0, 1, 55,
		 * 76543210).minusSeconds(i++)); trackInProgress.setRecordCar(LocalTime.of(0, 1,
		 * 52, 876543210)); trackInProgress.setRecordClass(LocalTime.of(0, 1, 51,
		 * 776543210)); trackInProgress.setRecordTrack(LocalTime.of(0, 1, 50,
		 * 676543210)); trackInProgress.setRecordClassCar("Mazda");
		 * trackInProgress.setRecordTrackCar("Mercedes / GTO");
		 */

		model.addAttribute(trackInProgress);
		return "starter :: data";
	}

	public void refreshTrackInProgress() {

		if (telemetryData.getGameSessionState() == null || telemetryData.getGameSessionState() == 1
				|| StringUtils.isEmpty(participantInfo.getCarName())) {
			trackInProgress.setTrackName(null);
			trackInProgress.setCarName(null);
			trackInProgress.setRecordSession(null);
			trackInProgress.setRecordCar(null);
			trackInProgress.setRecordClass(null);
			trackInProgress.setRecordTrack(null);
			trackInProgress.setRecordClassCar(null);
			trackInProgress.setRecordTrackCar(null);

		} else {

			// get record first time
			if (StringUtils.isEmpty(trackInProgress.getTrackName()) && StringUtils.isEmpty(trackInProgress.getCarName())
					&& !StringUtils.isEmpty(participantInfo.getCarName())
					&& !StringUtils.isEmpty(participantInfo.getTrackLocation())) {
				refreshRecordCar();
				refreshRecordClass();
				refreshRecordTrack();
			}

			trackInProgress.setTrackName(participantInfo.getTrackLocation());
			if (!StringUtils.isEmpty(participantInfo.getTrackVariation())) {
				trackInProgress
						.setTrackName(trackInProgress.getTrackName() + " / " + participantInfo.getTrackVariation());
			}
			trackInProgress.setCarName(participantInfo.getCarName());
			if (!StringUtils.isEmpty(participantInfo.getCarClassName())) {
				trackInProgress.setCarName(trackInProgress.getCarName() + " / " + participantInfo.getCarClassName());
			}

			if (telemetryData.getBestLapTime() > 0) {
				LocalTime bestLapSession = LocalTime.ofNanoOfDay((long) (telemetryData.getBestLapTime() * 1000000000));

				// if best record session is beaten and it was the ancien record car, class,
				// track, replace
				if (trackInProgress.getRecordSession() != null
						&& bestLapSession.isBefore(trackInProgress.getRecordSession())) {
					if (trackInProgress.getRecordSession().isBefore(trackInProgress.getRecordCar())) {
						trackInProgress.setRecordCar(trackInProgress.getRecordSession());
					}
					if (trackInProgress.getRecordSession().isBefore(trackInProgress.getRecordClass())) {
						trackInProgress.setRecordClass(trackInProgress.getRecordSession());
						trackInProgress.setRecordClassCar(participantInfo.getCarName());
					}
					if (trackInProgress.getRecordSession().isBefore(trackInProgress.getRecordTrack())) {
						trackInProgress.setRecordTrack(trackInProgress.getRecordSession());
						trackInProgress.setRecordTrackCar(participantInfo.getCarName());
						if (!StringUtils.isEmpty(participantInfo.getCarClassName())) {
							trackInProgress.setRecordTrackCar(
									trackInProgress.getRecordTrackCar() + " / " + participantInfo.getCarClassName());
						}
					}
				}

				trackInProgress.setRecordSession(bestLapSession);
			} else {
				// Refresh record if restart race
				if (trackInProgress.getRecordSession() != null) {
					refreshRecordCar();
					refreshRecordClass();
					refreshRecordTrack();
				}
				trackInProgress.setRecordSession(null);
			}

		}

	}

	private void refreshRecordCar() {
		LapRecord lapRecordCar = lapRecordRepo.findByLapKey_CarNameAndLapKey_TrackLocationAndLapKey_TrackVariation(
				participantInfo.getCarName(), participantInfo.getTrackLocation(), participantInfo.getTrackVariation());
		if (lapRecordCar != null) {
			trackInProgress.setRecordCar(lapRecordCar.getRecordLap());
		}

	}

	private void refreshRecordClass() {
		LapRecord lapRecordClass = lapRecordRepo
				.findFirstByClassNameAndLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(
						participantInfo.getCarClassName(), participantInfo.getTrackLocation(),
						participantInfo.getTrackVariation());
		if (lapRecordClass != null) {
			trackInProgress.setRecordClass(lapRecordClass.getRecordLap());
			trackInProgress.setRecordClassCar(lapRecordClass.getLapKey().getCarName());
		}
	}

	private void refreshRecordTrack() {
		LapRecord lapRecordTrack = lapRecordRepo
				.findFirstByLapKey_TrackLocationAndLapKey_TrackVariationOrderByRecordLapAsc(
						participantInfo.getTrackLocation(), participantInfo.getTrackVariation());
		if (lapRecordTrack != null) {
			trackInProgress.setRecordTrack(lapRecordTrack.getRecordLap());
			trackInProgress.setRecordTrackCar(lapRecordTrack.getLapKey().getCarName());
			if (!StringUtils.isEmpty(lapRecordTrack.getClassName())) {
				trackInProgress
						.setRecordTrackCar(trackInProgress.getRecordTrackCar() + " / " + lapRecordTrack.getClassName());
			}
		}
	}
}
