package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.PageResult;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Car;
import com.prestu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping("/find")
    public Result find() {
        List<Car> all = carService.findAll();
        return new Result(false,200,"chonggong",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody(required=false) Map searchMap) {
        Page<Car> page= carService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.SEARCH_SUCCESS,page.getResult(), (long) page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Car car) {
        Boolean add = carService.add(car);
        return new Result(add, StatusCode.OK, MessageConstant.ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Car community = carService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.FIND_BY_ID_SUCCESS, community);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Car car) {
        Boolean add = carService.update(car);
        return new Result(true, StatusCode.OK, MessageConstant.UPDATE_SUCCESS);
    }


    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag = carService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.DELETE_SUCCESS);
    }
}
