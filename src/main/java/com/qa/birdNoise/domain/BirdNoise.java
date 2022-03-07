package com.qa.birdNoise.domain;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class BirdNoise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String poster;
	private Date timeCreated;
	private String content;
	private Long likes;
	
	public BirdNoise() {
		super();
	}
	
	public BirdNoise(Long id, String poster, String content, Long likes) {
		super();
		this.id = id;
		this.poster = poster;
		this.timeCreated = Date.from(Instant.now());
		this.content = content;
		this.likes = likes;
	}
	
	public BirdNoise(BirdNoise in) {
		this(in.getId(), in.getPoster(), in.getContent(), in.getLikes());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}
	
	public void addLike() {
		this.likes++;
	}
	
}
