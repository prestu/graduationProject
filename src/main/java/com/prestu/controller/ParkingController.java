package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.Result;
import com.prestu.pojo.Parking;
import com.prestu.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    ParkingService parkingService;
    @RequestMapping("/search")
    public Result search(@RequestBody(required=false) Map searchMap) {
//        System.out.println(searchMap);
        Page<Parking> search = parkingService.search(searchMap);

        for (Parking parking : search) {
            System.out.println(parking);
        }
        return new Result(true,2000, MessageConstant.SEARCH_SUCCESS,search);
    }

}
