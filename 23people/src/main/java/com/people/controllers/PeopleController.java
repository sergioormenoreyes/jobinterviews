package com.people.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	

	@GetMapping(value = "people/{nationalId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getByNationalIdPath(@PathVariable String nationalId) {
		Person rsp = personsRepository.findByNationalId(nationalId);
		Optional<Person> opt = Optional.ofNullable(rsp);
		if(!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(rsp, HttpStatus.OK);
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
	
	@PutMapping(value = "people/{id}")
	public ResponseEntity<List<JSONObject>> updateById(@Valid @RequestBody PersonWrapper personsWrapper, @PathVariable String id) {
		try {
			for (Person person : personsWrapper.getPersons()) {
				if (id.equalsIgnoreCase(person.getNationalId())){
					personsRepository.save(person);
				} else {
					return new ResponseEntity<List<JSONObject>>(HttpStatus.NOT_FOUND);
				}
			}
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<JSONObject>>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "people/{id}")
	public ResponseEntity<List<JSONObject>> deleteById(@Valid @RequestBody PersonWrapper personsWrapper, @PathVariable String id) {
		try {
			for (Person person : personsWrapper.getPersons()) {
				if (id.equalsIgnoreCase(person.getNationalId())){
					personsRepository.delete(person);
				} else {
					return new ResponseEntity<List<JSONObject>>(HttpStatus.NOT_FOUND);
				}
			}
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<JSONObject>>(HttpStatus.OK);
	}
}
