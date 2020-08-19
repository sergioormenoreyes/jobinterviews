package com.people.controllers;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

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
import com.people.entity.PersonWrapper;
import com.people.repository.PersonRepository;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/") // This means URL's start with /demo (after Application path)
public class PeopleController {

	@Autowired
	PersonRepository personsRepository;

	@GetMapping(value = "people", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Person>> getAll() {
		return new ResponseEntity<Iterable<Person>>(personsRepository.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "people")
	public ResponseEntity<List<JSONObject>> saveAll(@Valid @RequestBody PersonWrapper persons) {
		try {
			for (Person person : persons.getPersons()) {
				personsRepository.save(person);
			}
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<JSONObject>>(HttpStatus.CREATED);
	}
}
