package com.thoughtworks.basicapp.service;

import com.thoughtworks.basicapp.domain.Car;
import com.thoughtworks.basicapp.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Car}.
 */
@Service
@Transactional
public class CarService {

    private final Logger log = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Save a car.
     *
     * @param car the entity to save.
     * @return the persisted entity.
     */
    public Car save(Car car) {
        log.debug("Request to save Car : {}", car);
        return carRepository.save(car);
    }

    /**
     * Get all the cars.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        log.debug("Request to get all Cars");
        return carRepository.findAll();
    }


    /**
     * Get one car by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Car> findOne(Long id) {
        log.debug("Request to get Car : {}", id);
        return carRepository.findById(id);
    }

    /**
     * Delete the car by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Car : {}", id);
        carRepository.deleteById(id);
    }
}
