package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

public interface CarService {
	
	Optional<CarModel> getCarDetailById(Long id);
	
	CarModel addCar(CarModel car);
	void deleteCar(CarModel car);
	void updateCar(CarModel car);
	List<CarModel> viewAllCar();
	
	CarDb getCarDb();
}