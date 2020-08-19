package com.people.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="person")
public class Person {
	
	@Id
	@NotBlank(message = "validation error")
	@Column(name="national_id")
	private String nationalId;
	
	@NotBlank(message = "validation error")
	@Column(name="name")
	private String name;
	
	@NotBlank(message = "validation error")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message= "validation error")
	@Range(min = 1)
	@Column(name="age")
	private Integer age;
	
	@NotBlank(message = "validation error")
	@Column(name="origin_planet")
	private String originPlanet;
	
	@NotBlank(message = "validation error")
	@Column(name="picture_url")
	private String pictureUrl;

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getOriginPlanet() {
		return originPlanet;
	}

	public void setOriginPlanet(String originPlanet) {
		this.originPlanet = originPlanet;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
}
