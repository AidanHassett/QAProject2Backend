package com.qa.birdNoise.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.birdNoise.domain.BirdNoise;
import com.qa.birdNoise.repo.BirdNoiseRepo;

@Service
public class BirdNoiseService implements ServiceInterface<BirdNoise> {

	private BirdNoiseRepo bnRepo;

	@Autowired
	public BirdNoiseService(BirdNoiseRepo repo) {
		this.bnRepo = repo;
	}

	public BirdNoise create(BirdNoise bn) {
		if (bn.getLikes() == null) {
			bn.setLikes(0);
		}
		if (bn.getTimeCreated() == null) {
			bn.setTimeCreated(Date.from(Instant.now()));
		}
		return this.bnRepo.save(bn);
	}

	public List<BirdNoise> getAll() {
		return this.bnRepo.findAll();
	}

	public BirdNoise get(Integer id) {
		try {
			return this.bnRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<BirdNoise> getbyPoster(String poster) {
		return this.bnRepo.findByPoster(poster);
	}

	public boolean replace(BirdNoise bn) {
		BirdNoise existing;
		boolean exists;
		try {
			existing = this.bnRepo.findById(bn.getId()).get();
			existing.setPoster(bn.getPoster());
			existing.setContent(bn.getContent());
			existing.setTimeCreated(bn.getTimeCreated());
			existing.setLikes(bn.getLikes());
			exists = true;
		} catch (NoSuchElementException e) {
			existing = new BirdNoise(bn);
			exists = false;
		}
		this.bnRepo.save(existing);
		return exists;
	}

	public boolean addLike(Integer id) {
		try {
			BirdNoise bn = this.bnRepo.findById(id).get();
			bn.addLike();
			this.bnRepo.save(bn);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean remove(Integer id) {
		if (this.bnRepo.existsById(id)) {
			this.bnRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}



}
