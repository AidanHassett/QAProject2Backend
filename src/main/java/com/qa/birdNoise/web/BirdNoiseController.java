package com.qa.birdNoise.web;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.birdNoise.domain.BirdNoise;
import com.qa.birdNoise.service.BirdNoiseService;

@RestController
public class BirdNoiseController {

	BirdNoiseService bnServ;

	@Autowired
	public BirdNoiseController(BirdNoiseService bnServ) {
		super();
		this.bnServ = bnServ;
	}

	@PostMapping("/createBirdNoise")
	public ResponseEntity<BirdNoise> create(@RequestBody BirdNoise bn) {
		BirdNoise created = this.bnServ.create(bn);
		return new ResponseEntity<BirdNoise>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getAllBirdNoises")
	public ResponseEntity<List<BirdNoise>> getAll() {
		return ResponseEntity.ok(this.bnServ.getAll());
	}

	@GetMapping("/getBirdNoise/{id}")
	public ResponseEntity<BirdNoise> get(@PathVariable Integer id) {
		BirdNoise an = this.bnServ.get(id);
		HttpStatus status;
		if (an == null) {
			status = HttpStatus.NOT_FOUND;
		} else {
			status = HttpStatus.OK;
		}
		return new ResponseEntity<BirdNoise>(an, status);
	}

	@GetMapping("/getBirdNoiseByPoster/{poster}")
	public ResponseEntity<List<BirdNoise>> getByPoster(@PathVariable String poster) {
		List<BirdNoise> bn = this.bnServ.getbyPoster(poster);
		HttpStatus status;

		if (bn.isEmpty()) {
			status = HttpStatus.NOT_FOUND;
		} else {
			bn.sort(new Comparator<BirdNoise>() {
				public int compare(BirdNoise bn1, BirdNoise bn2) {
					return bn1.getTimeCreated().compareTo(bn2.getTimeCreated());
				}
			});
			status = HttpStatus.OK;
		}

		return new ResponseEntity<List<BirdNoise>>(bn, status);
	}

	@PutMapping("/replaceBirdNoise")
	public ResponseEntity<?> replace(@RequestBody BirdNoise bn) {
		HttpStatus status;
		if (this.bnServ.replace(bn)) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.CREATED;
		}
		return new ResponseEntity<>(status);
	}
	
	@PutMapping("/likeBirdNoise/{id}")
	public ResponseEntity<?> like(@PathVariable Integer id) {
		if (this.bnServ.addLike(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@DeleteMapping("/deleteBirdNoise/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		if (this.bnServ.remove(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
