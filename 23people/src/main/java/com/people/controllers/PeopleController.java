package com.people.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.people.entity.Person;
import com.people.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // This means that this class is a Controller
@RequestMapping(path = "/") // This means URL's start with /demo (after Application path)
public class PeopleController {

	@Autowired
	PersonRepository personsRepository;

	@GetMapping(value = "people", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> getAll() {
		List<JSONObject> rt = toJson(personsRepository.findAll());
		return new ResponseEntity<List<JSONObject>>(rt, HttpStatus.OK);
	}

	private List<JSONObject> toJson(Iterable<Person> persons) {
		List<JSONObject> entities = new ArrayList<JSONObject>();
		for (Person n : persons) {
			JSONObject json = new JSONObject();
			json.put("nationalId", n.getNationalId());
			json.put("name", n.getName());
			json.put("lastName", n.getLastName());
			json.put("age", n.getAge());
			json.put("orginPlanet", n.getOriginPlanet());
			json.put("pictureUrl", n.getPictureUrl());
			entities.add(json);
		}
		return entities;
	}

	@PostMapping(value = "people",  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<JSONObject>> saveAll(@RequestBody Person person) {
		try {
			System.out.println(person);
		} catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<JSONObject>>(HttpStatus.CREATED);
	}
}
