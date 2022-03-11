package com.qa.birdNoise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.birdNoise.domain.BirdNoise;

public interface BirdNoiseRepo extends JpaRepository<BirdNoise, Integer> {

	List<BirdNoise> findByPoster(String poster);

}
