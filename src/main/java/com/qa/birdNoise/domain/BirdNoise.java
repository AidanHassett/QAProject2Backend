package com.qa.birdNoise.domain;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;


@Entity
public class BirdNoise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String poster;
	private Date timeCreated;
	private String content;
	@Value("0")
	private Integer likes;

	public BirdNoise() {
		super();
	}
	
	public BirdNoise(Integer id, String poster, Date timeCreated, String content, Integer likes) {
		super();
		this.id = id;
		this.poster = poster;
		this.timeCreated = timeCreated;
		this.content = content;
		this.likes = likes;
	}
	
	public BirdNoise(Integer id, String poster, String content) {
		this(id, poster, Date.from(Instant.now()), content, 0);
	}
	
	public BirdNoise(String poster, String content) {
		this(null, poster, content);
	}

	public BirdNoise(BirdNoise in) {
		this(in.getId(), in.getPoster(), in.getContent());
		this.setLikes(in.getLikes());
		this.setTimeCreated(in.getTimeCreated());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public void addLike() {
		this.likes++;
	}

}
