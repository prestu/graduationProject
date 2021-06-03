package com.prestu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prestu.common.MessageConstant;
import com.prestu.common.Result;
import com.prestu.mapper.ComplaintMapper;
import com.prestu.pojo.Activity;
import com.prestu.pojo.Complaint;
import com.prestu.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {
    @Autowired
    private ComplaintMapper complaintMapper;
    @Override
    public Page<Complaint> search(Map searchMap) {
        int pageNum=  1;
        int pageSize = 10;
        String sub = "00:00:00";
        String pre = "23:59:59";
        Example example = new Example(Complaint.class);
        if (searchMap != null) {

            Example.Criteria criteria = example.createCriteria();
            String communityName = (String) searchMap.get("communityName");
            String start = (String) searchMap.get("startTime");
            String end = (String) searchMap.get("endTime");
            Integer num = (Integer) searchMap.get("pageNum");
            Integer size = (Integer) searchMap.get("pageSize");
            if (StringUtil.isNotEmpty(end)) {
                start = start.substring(0, 11) + sub;
            }
            if (StringUtil.isNotEmpty(end)) {
                end = end.substring(0, 11) + pre;
            }
            if (StringUtil.isNotEmpty(start)) {
                criteria.andGreaterThanOrEqualTo("startTime", start);
            }
            if (StringUtil.isNotEmpty(end)) {
                criteria.andLessThanOrEqualTo("startTime", end);
            }
            if (StringUtil.isNotEmpty(communityName)) {
                criteria.andLike("communityName", "%" + communityName + "%");
            }
            if (num != null) {
                pageNum = num;
            }
            if (size != null) {
                pageSize = size;
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        Page<Complaint> complaints = (Page<Complaint>)complaintMapper.selectByExample(example);
        return complaints;
    }

    @Override
    public Result add(Complaint complaint) {
        int insert = complaintMapper.insert(complaint);
        return new Result(insert > 0,2000, MessageConstant.ADD_SUCCESS);
    }

    @Override
    public Complaint findById(Integer id) {
        Complaint complaint = complaintMapper.selectByPrimaryKey(id);
        return complaint;
    }

    @Override
    public Result update(Complaint complaint) {
        int i = complaintMapper.updateByPrimaryKey(complaint);
        return new Result(i > 0,2000, MessageConstant.UPDATE_SUCCESS);
    }

    @Override
    public Result del(List<Integer> ids) {
        for (Integer id : ids) {
            int i = complaintMapper.deleteByPrimaryKey(id);
        }
        return  new Result(true,2000, MessageConstant.DELETE_SUCCESS);
    }
}
