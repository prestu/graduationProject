package com.prestu.controller;


import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Activity;
import com.prestu.pojo.Parking;
import com.prestu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @RequestMapping("/search")
    public Result search(@RequestBody(required=false) Map searchMap) {
//        System.out.println(searchMap);
        Page<Activity> search = activityService.search(searchMap);

        for (Activity activity: search) {
            System.out.println(activity);
        }
        return new Result(true,2000, MessageConstant.SEARCH_SUCCESS,search);
    }

    @RequestMapping("/del")
    public Result delete(@RequestBody(required = false) List<Integer> ids) {
        Boolean del = activityService.del(ids);
        return new Result(del, StatusCode.OK, MessageConstant.DELETE_SUCCESS);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody(required = false) Activity activity) {
        boolean add = activityService.add(activity);
        return new Result(add, StatusCode.OK, MessageConstant.ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Activity byId = activityService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.FIND_BY_ID_SUCCESS,byId);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody(required = false)Activity activity) {
        Boolean update = activityService.update(activity);
        return new Result(update,StatusCode.OK,MessageConstant.UPDATE_SUCCESS);
    }
}
