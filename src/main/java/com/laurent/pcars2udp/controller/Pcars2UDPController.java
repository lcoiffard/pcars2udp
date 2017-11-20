package com.laurent.pcars2udp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.laurent.pcars2udp.dto.ParticipantInfo;
import com.laurent.pcars2udp.dto.TelemetryData;
import com.laurent.pcars2udp.dto.TimeLap;
import com.laurent.pcars2udp.dto.TrackInProgress;
import com.laurent.pcars2udp.entity.Car;
import com.laurent.pcars2udp.entity.LapRecord;
import com.laurent.pcars2udp.entity.Track;
import com.laurent.pcars2udp.repo.CarRepo;
import com.laurent.pcars2udp.repo.LapRecordRepo;
import com.laurent.pcars2udp.repo.TrackRepo;

@Controller
@SessionAttributes({ "carList", "classList", "trackList" })
public class Pcars2UDPController {

	@Autowired
	private ParticipantInfo participantInfo;

	@Autowired
	private TelemetryData telemetryData;

	@Autowired
	private LapRecordRepo lapRecordRepo;

	@Autowired
	private CarRepo carRepo;

	@Autowired
	private TrackRepo trackRepo;

	@Autowired
	private TrackInProgress trackInProgress;

	int i = 0;

	@RequestMapping(value = "/getRecords", method = RequestMethod.POST)
	public String getLapRecord(Long idCar, String className, Long idTrack, Model model) {

		Page<LapRecord> records = null;

		if (idCar != null || !StringUtils.isEmpty(className) || idTrack != null) {

			LapRecord lapRecord = new LapRecord();
			lapRecord.setCar(new Car());
			lapRecord.getCar().setId(idCar);
			lapRecord.getCar().setClassName(StringUtils.isEmpty(className) ? null : className);
			lapRecord.setTrack(new Track());
			lapRecord.getTrack().setId(idTrack);

			ExampleMatcher matcher = ExampleMatcher.matching();

			Example<LapRecord> example = Example.of(lapRecord, matcher);

			records = lapRecordRepo.findAll(example, new PageRequest(0, 50, new Sort(Sort.Direction.ASC, "recordLap")));
		}

		model.addAttribute("recordsList", records);

		return "pcars2udp :: records";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		refreshTrackInProgress();

		model.addAttribute(trackInProgress);
		model.addAttribute("carList", carRepo.findAllByOrderByCarName());
		model.addAttribute("classList", carRepo.findAllClassByOrderByClassName());
		model.addAttribute("trackList", trackRepo.findAllByOrderByTrackLocationAscTrackVariationAsc());

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

		// trackInProgress.getRecordSession().setTimeLap(LocalDateTime.of(0, 1, 55,
		// 76543210).minusSeconds(i++));
		//
		// trackInProgress.getRecordSession().setRecordSectorOne(LocalDateTime.of(0, 0,
		// 19,
		// 876543210).minusSeconds(i));
		// trackInProgress.getRecordSession().setRecordSectorTwo(LocalDateTime.of(0, 1,
		// 5,
		// 876543210).minusSeconds(i));
		// trackInProgress.getRecordSession().setRecordSectorThree(LocalDateTime.of(0,
		// 0,
		// 37, 876543210).minusSeconds(i));

		// trackInProgress.getRecordCar().setTimeLap(LocalDateTime.of(0, 1, 52,
		// 876543210));
		// trackInProgress.getRecordCar().setTimeSectorOne(LocalDateTime.of(0, 0, 17,
		// 876543210));
		// trackInProgress.getRecordCar().setTimeSectorTwo(LocalDateTime.of(0, 1, 1,
		// 876543210));
		// trackInProgress.getRecordCar().setTimeSectorThree(LocalDateTime.of(0, 0, 34,
		// 876543210));

		// trackInProgress.getRecordClass().setRecord(LocalDateTime.of(0, 1, 51,
		// 776543210));
		// trackInProgress.getRecordTrack().setRecord(LocalDateTime.of(0, 1, 50,
		// 676543210));
		// trackInProgress.getRecordClass().setCarName("Mazda");
		// trackInProgress.getRecordTrack().setCarName("Mercedes");
		// trackInProgress.getRecordTrack().setClassName("GTO");

		model.addAttribute(trackInProgress);
		return "pcars2udp :: data";
	}

	public void refreshTrackInProgress() {

		// if we are on track
		if (telemetryData.getGameSessionState() != null && telemetryData.getGameSessionState() != 1
				&& !StringUtils.isEmpty(participantInfo.getCarName())) {

			// if car/track change, refresh
			if (!StringUtils.isEmpty(participantInfo.getCarName())
					&& !StringUtils.isEmpty(participantInfo.getTrackLocation())
					// && !StringUtils.isEmpty(participantInfo.getTrackVariation())
					&& (!StringUtils.equals(trackInProgress.getRecordSession().getCarName(),
							participantInfo.getCarName())
							|| !StringUtils.equals(trackInProgress.getRecordSession().getTrackName(),
									participantInfo.getTrackLocation())
							|| !StringUtils.equals(trackInProgress.getRecordSession().getTrackVariation(),
									participantInfo.getTrackVariation()))) {

				trackInProgress.getRecordSession().setTrackName(participantInfo.getTrackLocation());
				trackInProgress.getRecordSession().setTrackVariation(participantInfo.getTrackVariation());
				trackInProgress.getRecordSession().setCarName(participantInfo.getCarName());
				trackInProgress.getRecordSession().setClassName(participantInfo.getCarClassName());

				trackInProgress.getRecordSession().resetTime();

				refreshRecordCar();
				refreshRecordClass();
				refreshRecordTrack();

			}

			if (telemetryData.getBestLapTime() > 0) {
				LocalDateTime bestLapSession = LocalDateTime.of(LocalDate.ofEpochDay(0),
						LocalTime.ofNanoOfDay((long) (telemetryData.getBestLapTime() * 1000000000)));

				LocalDateTime bestLapSessionSectorOne = LocalDateTime.of(LocalDate.ofEpochDay(0),
						LocalTime.ofNanoOfDay((long) (telemetryData.getFastestSector1Time() * 1000000000)));
				LocalDateTime bestLapSessionSectorTwo = LocalDateTime.of(LocalDate.ofEpochDay(0),
						LocalTime.ofNanoOfDay((long) (telemetryData.getFastestSector2Time() * 1000000000)));
				LocalDateTime bestLapSessionSectorThree = LocalDateTime.of(LocalDate.ofEpochDay(0),
						LocalTime.ofNanoOfDay((long) (telemetryData.getFastestSector3Time() * 1000000000)));

				// if best record session is beaten and it was the ancien record car, class,
				// track, replace
				if (trackInProgress.getRecordSession().isTimeBeaten(bestLapSession)) {
					if (trackInProgress.getRecordCar().isTimeBeaten(trackInProgress.getRecordSession().getTimeLap())) {
						trackInProgress.setRecordCar(trackInProgress.getRecordSession().clone());
					}
					if (trackInProgress.getRecordClass()
							.isTimeBeaten(trackInProgress.getRecordSession().getTimeLap())) {
						trackInProgress.setRecordClass(trackInProgress.getRecordSession().clone());
					}
					if (trackInProgress.getRecordTrack()
							.isTimeBeaten(trackInProgress.getRecordSession().getTimeLap())) {
						trackInProgress.setRecordTrack(trackInProgress.getRecordSession().clone());
					}
				}

				trackInProgress.getRecordSession().setTimeLap(bestLapSession);
				trackInProgress.getRecordSession().setTimeSectorOne(bestLapSessionSectorOne);
				trackInProgress.getRecordSession().setTimeSectorTwo(bestLapSessionSectorTwo);
				trackInProgress.getRecordSession().setTimeSectorThree(bestLapSessionSectorThree);
			} else {
				// Refresh record if restart race
				if (trackInProgress.getRecordSession().getTimeLap() != null) {
					refreshRecordCar();
					refreshRecordClass();
					refreshRecordTrack();
				}
				trackInProgress.getRecordSession().resetTime();
			}

		}

	}

	private void refreshRecordCar() {
		trackInProgress.getRecordCar().reset();
		LapRecord lapRecordCar = lapRecordRepo.findByCar_CarNameAndTrack_TrackLocationAndTrack_TrackVariation(
				participantInfo.getCarName(), participantInfo.getTrackLocation(), participantInfo.getTrackVariation());

		if (lapRecordCar != null) {
			refreshRecord(trackInProgress.getRecordCar(), lapRecordCar);
		}
	}

	private void refreshRecord(TimeLap record, LapRecord lapRecord) {
		record.setTrackName(lapRecord.getTrack().getTrackLocation());
		record.setTrackVariation(lapRecord.getTrack().getTrackVariation());
		record.setCarName(lapRecord.getCar().getCarName());
		record.setClassName(lapRecord.getCar().getClassName());
		record.setTimeLap(lapRecord.getRecordLap());
		record.setTimeSectorOne(lapRecord.getRecordSectorOne());
		record.setTimeSectorTwo(lapRecord.getRecordSectorTwo());
		record.setTimeSectorThree(lapRecord.getRecordSectorThree());
		record.setDateRecord(lapRecord.getRecordDate());

	}

	private void refreshRecordClass() {

		trackInProgress.getRecordClass().reset();
		LapRecord lapRecordClass = lapRecordRepo
				.findFirstByCar_ClassNameAndTrack_TrackLocationAndTrack_TrackVariationOrderByRecordLapAsc(
						participantInfo.getCarClassName(), participantInfo.getTrackLocation(),
						participantInfo.getTrackVariation());

		if (lapRecordClass != null) {
			refreshRecord(trackInProgress.getRecordClass(), lapRecordClass);
		}
	}

	private void refreshRecordTrack() {
		trackInProgress.getRecordTrack().reset();
		LapRecord lapRecordTrack = lapRecordRepo
				.findFirstByTrack_TrackLocationAndTrack_TrackVariationOrderByRecordLapAsc(
						participantInfo.getTrackLocation(), participantInfo.getTrackVariation());
		if (lapRecordTrack != null) {
			refreshRecord(trackInProgress.getRecordTrack(), lapRecordTrack);
		}
	}

}
