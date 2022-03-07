package com.qa.birdNoise.service;

import java.util.List;

public interface ServiceInterface<T> {
	
	public T create (T t);
	public List<T> getAll();
	public T get(Long id);
	public boolean replace(T t);
	public boolean remove(Long id);

}
