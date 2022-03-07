package com.qa.birdNoise.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;

import com.qa.birdNoise.domain.BirdNoise;
import com.qa.birdNoise.repo.BirdNoiseRepo;

public class BirdNoiseService implements ServiceInterface<BirdNoise> {
	
	private BirdNoiseRepo bnRepo;
	
	@Autowired
	public BirdNoiseService(BirdNoiseRepo repo) {
		this.bnRepo = repo;
	}
	
	public BirdNoise create(BirdNoise bn) {
		return this.bnRepo.save(bn);
	}
	
	public List<BirdNoise> getAll() {
		return this.bnRepo.findAll();
	}

	public BirdNoise get(Long id) {
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
	
	public boolean addLike(Long id) {
		try {
			BirdNoise bn = this.bnRepo.findById(id).get();
			bn.addLike();
			this.bnRepo.save(bn);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@Override
	public boolean remove(Long id) {
		if (this.bnRepo.existsById(id)) {
			this.bnRepo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	

}
