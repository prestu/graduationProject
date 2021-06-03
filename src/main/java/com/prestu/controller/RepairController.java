package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Repair;
import com.prestu.service.RepairService;
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
@RequestMapping("/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;
    @RequestMapping("/search")
    public Result search(@RequestBody(required=false) Map searchMap) {
        Page<Repair> search = repairService.search(searchMap);
        return new Result(true,2000, MessageConstant.SEARCH_SUCCESS,search);
    }

    @RequestMapping("/del")
    public Result delete(@RequestBody(required = false) List<Integer> ids) {
        Boolean del = repairService.del(ids);
        return new Result(del, StatusCode.OK, MessageConstant.DELETE_SUCCESS);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody(required = false) Repair repair) {
        Boolean add = repairService.add(repair);
        return new Result(add, StatusCode.OK, MessageConstant.ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Repair byId = repairService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.FIND_BY_ID_SUCCESS,byId);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody(required = false)Repair repair ) {
        Boolean update = repairService.update(repair);
        return new Result(update,StatusCode.OK,MessageConstant.UPDATE_SUCCESS);
    }
}
