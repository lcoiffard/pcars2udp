package com.laurent.pcars2udp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "CAR", indexes = { @Index(name = "IDX_CARNAME", columnList = "carName", unique = true),
		@Index(name = "IDX_CLASSNAME", columnList = "className") })
public class Car {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String carName;
	@Column(nullable = false)
	private String className;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
