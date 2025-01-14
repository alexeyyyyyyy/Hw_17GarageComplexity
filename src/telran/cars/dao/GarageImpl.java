package telran.cars.dao;

import java.util.function.Predicate;

import telran.cars.model.Car;

public class GarageImpl implements Garage {
	private Car[] cars;
	private int size;

	public GarageImpl(int capacity) {
		cars = new Car[capacity];
	}

	@Override // O(n) - linear 
	public boolean addCar(Car car) {
		if (car == null || size == cars.length || findCarByRegNumber(car.getRegNumber()) != null) {
			return false;
		}
		cars[size++] = car;
		return true;
	}

	@Override // O(n) - linear 
	public Car removeCar(String regNumber) {
		for (int i = 0; i < size; i++) {
			if (regNumber.equals(cars[i].getRegNumber())) {
				Car temp = cars[i];
				cars[i] = cars[--size];
				return temp;
			}

		}
		return null;
	}

	@Override // O(n) - linear 
	public Car findCarByRegNumber(String regNumber) {
		for (int i = 0; i < size; i++) {
			if (cars[i].getRegNumber().equals(regNumber)) {
				return cars[i];
			}
		}
		return null;
	}

	@Override // O(n) - linear 
	public Car[] findCarsByModel(String model) {
		return findCarsByPredicate(c -> model.equals(c.getModel()));
	}

	@Override // O(n) - linear 
	public Car[] findCarsByCompany(String company) {
		return findCarsByPredicate(c -> company.equals(c.getCompany()));
	}

	@Override // O(n) - linear 
	public Car[] findCarsByEngine(double min, double max) {
		return findCarsByPredicate(c -> c.getEngine() >= min && c.getEngine() < max);
	}

	@Override // O(n) - linear 
	public Car[] findCarsByColor(String color) {
		return findCarsByPredicate(c -> color.equals(c.getColor()));
	}

	private Car[] findCarsByPredicate(Predicate<Car> predicate) { // O(n) - linear 
		int count = 0;
		for (int i = 0; i < size; i++) {
			if (predicate.test(cars[i])) {
				count++;
			}
		}
		Car[] res = new Car[count];
		for (int i = 0, j = 0; j < res.length; i++) {
			if (predicate.test(cars[i])) {
				res[j++] = cars[i];
			}
		}
		return res;
	}

}
