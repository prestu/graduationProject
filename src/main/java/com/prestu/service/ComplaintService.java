package com.prestu.service;

import com.github.pagehelper.Page;
import com.prestu.common.Result;
import com.prestu.pojo.Complaint;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
public interface ComplaintService {

    Page<Complaint> search(Map searchMap) ;

    Result add(Complaint complaint) ;


    Complaint findById(Integer id);

    Result update(Complaint complaint);


    Result del(List<Integer> ids);
}
