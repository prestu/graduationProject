package com.prestu.service;

import com.github.pagehelper.Page;
import com.prestu.pojo.Parking;
import com.prestu.pojo.Repair;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
public interface RepairService {
    List<Repair> findAll();

    Page<Repair> search(Map searchMap) ;

    Boolean add(Repair repair) ;


    Repair findById(Integer id);

    Boolean update(Repair repair);


    Boolean del(List<Integer> ids);
}
