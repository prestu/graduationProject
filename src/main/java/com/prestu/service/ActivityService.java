package com.prestu.service;

import com.github.pagehelper.Page;
import com.prestu.pojo.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
public interface ActivityService {
    List<Activity> findAll();

    Page<Activity> search(Map searchMap) ;

    boolean add(Activity activity) ;


    Activity findById(Integer id);

    Boolean update(Activity activity);


    Boolean del(List<Integer> ids);
}
