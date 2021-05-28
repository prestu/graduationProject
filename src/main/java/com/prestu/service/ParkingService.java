package com.prestu.service;

import com.github.pagehelper.Page;
import com.prestu.pojo.Parking;

import java.util.List;
import java.util.Map;

public interface ParkingService {
    List<Parking> findAll();

    Page<Parking> search(Map searchMap) ;

    Boolean add(Parking parking) ;


    Parking findById(Integer id);

    Boolean update(Parking parking);


    Boolean del(List<Integer> ids);
}
