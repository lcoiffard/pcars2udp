package com.laurent.pcars2udp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.laurent.pcars2udp.entity.Car;

public interface CarRepo extends JpaRepository<Car, Long> {

	 Car findByCarName(String carName);

	 List<Car> findAllByOrderByCarName();

	@Query(value = "SELECT distinct className FROM Car order by className ")
	 List<String> findAllClassByOrderByClassName();
}
