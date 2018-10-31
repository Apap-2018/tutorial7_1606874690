package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;

	@PostMapping()
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		return carService.addCar(car);
	}

	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable ("carId") long carId, Model model) {
		CarModel car = carService.getCarDetailById(carId).get();
	//	car.setDealer(null);
		return car;
	}

	@DeleteMapping(value = "/{carId}")
	private String deleteCar(@PathVariable("carId") long id, Model model) {
		CarModel car = carService.getCarDetailById(id).get();
		carService.deleteCar(car);
		return "Car has been deleted";
	}

	@PutMapping(value = "/{carId}")
	private String updateCarSubmit(
			@PathVariable (value = "carId") long carId,
			@RequestParam("brand") String brand,
			@RequestParam("type") String type,
			@RequestParam("price") String price,
			@RequestParam("amount") String amount,
			@RequestParam("dealerId") DealerModel dealer) {
		
		CarModel car = (CarModel) carService.getCarDetailById(carId).get();
		
		if(car.equals(null)) {
			return "Couldn't find your car";
			}
		
		car.setBrand(brand);
		car.setType(type);
		car.setPrice(Long.parseLong(price));
		car.setAmount(Integer.parseInt(amount));

		carService.updateCar(car);
		return "Car update success";
		
		}

	@GetMapping()
	private List<CarModel> viewAllCar(Model model) {
		List<CarModel> listCar = carService.viewAllCar();

//		for (int i = 0; i < listCar.size(); i++) {
//			listCar.get(i).setDealer(null);
//		}
		
		return listCar;
	}
}