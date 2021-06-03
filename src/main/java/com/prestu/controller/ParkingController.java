package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Parking;
import com.prestu.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    ParkingService parkingService;
    @RequestMapping("/search")
    public Result search(@RequestBody(required=false) Map searchMap) {
        Page<Parking> search = parkingService.search(searchMap);

        for (Parking parking : search) {
            System.out.println(parking);
        }
        return new Result(true,2000, MessageConstant.SEARCH_SUCCESS,search);
    }

    @RequestMapping("/del")
    public Result delete(@RequestBody(required = false) List<Integer> ids) {
        Boolean del = parkingService.del(ids);
        return new Result(del, StatusCode.OK, MessageConstant.DELETE_SUCCESS);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody(required = false) Parking parking ) {
        Boolean add = parkingService.add(parking);
        return new Result(add, StatusCode.OK, MessageConstant.ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Parking byId = parkingService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.FIND_BY_ID_SUCCESS,byId);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody(required = false)Parking parking ) {
        Boolean update = parkingService.update(parking);
        return new Result(update,StatusCode.OK,MessageConstant.UPDATE_SUCCESS);
    }
}
