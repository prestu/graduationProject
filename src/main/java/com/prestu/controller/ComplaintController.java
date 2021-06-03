package com.prestu.controller;

import com.github.pagehelper.Page;
import com.prestu.common.MessageConstant;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import com.prestu.pojo.Complaint;
import com.prestu.service.ComplaintService;
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
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;
    @RequestMapping("/search")
    public Result search(@RequestBody(required=false) Map searchMap) {
        Page<Complaint> search = complaintService.search(searchMap);
        return new Result(true,2000, MessageConstant.SEARCH_SUCCESS,search);
    }
    @RequestMapping("/del")
    public Result delete(@RequestBody(required = false) List<Integer> ids) {
        Result del1 = complaintService.del(ids);
        return del1;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody(required = false) Complaint complaint ) {
        Result add = complaintService.add(complaint);
        return add;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        Complaint byId = complaintService.findById(id);
        return new Result(true,StatusCode.OK,MessageConstant.FIND_BY_ID_SUCCESS,byId);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody(required = false)Complaint complaint) {
        Result update = complaintService.update(complaint);
        return update;
    }

}
