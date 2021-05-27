package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.PageResult;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Community;
import com.prestu.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
@RestController
@RequestMapping("/community")

public class CommunityController {


    @Autowired
    private CommunityService communityService;

    @RequestMapping("/find")
    public Result find() {
        List<Community> all = communityService.findAll();
        return new Result(false,200,"chonggong",all);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody(required=false) Map searchMap) {
        Page<Community> page= communityService.search(searchMap);
        return new PageResult(true, StatusCode.OK, MessageConstant.SEARCH_SUCCESS,page.getResult(), (long) page.getTotal());
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Community community) {
        Boolean add = communityService.add(community);
        return new Result(add, StatusCode.OK, MessageConstant.ADD_SUCCESS);
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Community community = communityService.findById(id);
        return new Result(true, StatusCode.OK, MessageConstant.FIND_BY_ID_SUCCESS, community);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Community community) {
        Boolean add = communityService.update(community);
        return new Result(true, StatusCode.OK, MessageConstant.UPDATE_SUCCESS);
    }

    // /community/updateStatus/0/1
    @RequestMapping("/updateStatus/{status}/{id}")
    public Result updateStatus(@PathVariable("status") String status, @PathVariable("id") Integer id) {
        Boolean flag = communityService.updateStatus(status, id);
        return new Result(true, StatusCode.OK, MessageConstant.UPDATE_STATUS_SUCCESS);
    }

    @RequestMapping("/del")
    public Result del(@RequestBody List<Integer> ids) {
        Boolean flag = communityService.del(ids);
        return new Result(true, StatusCode.OK, MessageConstant.DELETE_SUCCESS);
    }

}
