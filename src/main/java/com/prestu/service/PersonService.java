package com.prestu.service;

import com.github.pagehelper.Page;
import com.prestu.pojo.Car;
import com.prestu.pojo.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface PersonService {
    Page<Person> search(Map searchMap);
    Person searchByName(String username);
    public List<Person> findAll();

    public Boolean add(Person person) ;


    public Person findById(Integer id);

    public Boolean update(Person car);


    public Boolean del(List<Integer> ids);
}
