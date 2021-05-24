package com.prestu.service;

import com.github.pagehelper.Page;
import com.prestu.pojo.Car;
import com.prestu.pojo.Community;

import java.util.List;
import java.util.Map;

public interface CarService {
    public List<Car> findAll();

    public Page<Car> search(Map searchMap) ;

    public Boolean add(Car car) ;


    public Car findById(Integer id);

    public Boolean update(Car car);


    public Boolean del(List<Integer> ids);
}
