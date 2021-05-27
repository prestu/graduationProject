package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.PageResult;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Building;
import com.prestu.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auth:
 * @Desc:
 */
@RestController
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @RequestMapping("/find")

    public Result find() {
        List<Building> all = buildingService.findAll();
        return new Result(false, 200, "请求成功", all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody Map searchMap) {
        Page<Building> page = buildingService.search(searchMap);
//        List<Building> result = page.getResult();
        return new PageResult(true, StatusCode.OK, MessageConstant.SEARCH_SUCCESS, page.getResult(), page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Building building) {
        Boolean add = buildingService.add(building);
        return new Result(add, StatusCode.OK, MessageConstant.ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Building community = buildingService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.FIND_BY_ID_SUCCESS, community);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Building community) {
        Boolean add = buildingService.update(community);
        return new Result(add, StatusCode.OK, MessageConstant.UPDATE_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag = buildingService.del(ids);
        return new Result(flag, StatusCode.OK, MessageConstant.DELETE_SUCCESS);
    }
}
