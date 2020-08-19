package com.people.repository;

import org.springframework.data.repository.CrudRepository;

import com.people.entity.Person;


public interface PersonRepository extends CrudRepository<Person, Long> {

}
