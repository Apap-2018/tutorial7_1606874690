package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

@Service
@Transactional
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarDb carDb;
	
	@Override
	public CarModel addCar(CarModel car) {
		carDb.save(car);
		return car;
	}

	@Override
	public CarDb getCarDb() {
		return carDb;
	}
	
	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}
	
	@Override
	public Optional<CarModel> getCarDetailById(Long id) {
		return carDb.findById(id);
	}
	
	@Override
	public void updateCar(CarModel car) {
		for (int i = 0; i < carDb.findAll().size(); i++) {
			if (carDb.findAll().get(i).getId() == (car.getId())) {
				
				CarModel archive = carDb.findAll().get(i);
				int idx = carDb.findAll().indexOf(archive);
				
				carDb.findAll().get(idx).setBrand(car.getBrand());
				carDb.findAll().get(idx).setType(car.getType());
				carDb.findAll().get(idx).setPrice(car.getPrice());
				carDb.findAll().get(idx).setAmount(car.getAmount());
			}
		}
	}
	
	@Override
	public List<CarModel> viewAllCar() {
		
		List<CarModel> listCar = this.carDb.findAll();
		
		return listCar;
	}


}
