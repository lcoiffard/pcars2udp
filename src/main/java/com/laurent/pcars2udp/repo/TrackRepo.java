package com.laurent.pcars2udp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laurent.pcars2udp.entity.Track;

public interface TrackRepo extends JpaRepository<Track, Long> {

	public Track findByTrackLocationAndTrackVariation(String trackLocation, String trackVariation);

	public List<Track> findAllByOrderByTrackLocationAscTrackVariationAsc();

}
