package com.prestu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prestu.mapper.RepairMapper;
import com.prestu.pojo.Activity;
import com.prestu.pojo.Repair;
import com.prestu.service.RepairService;
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
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairMapper repairMapper;
    @Override
    public List<Repair> findAll() {
        return repairMapper.selectAll();
    }

    @Override
    public Page<Repair> search(Map searchMap) {
        int pageNum=  1;
        int pageSize = 2;
        String sub = "00:00:00";
        String pre = "23:59:59";
        Example example = new Example(Repair.class);
        Example.Criteria criteria = example.createCriteria();
        String ownerName = (String)searchMap.get("ownerName");
        String start = (String)searchMap.get("startTime");
        String end = (String)searchMap.get("endTime");
        Integer num = (Integer)searchMap.get("pageNum");
        Integer size = (Integer)searchMap.get("pageSize");
        if (StringUtil.isNotEmpty(end)) {
            start =start.substring(0,11)+sub;
        }
        if (StringUtil.isNotEmpty(end)) {
            end =end.substring(0,11)+pre;
        }
        if (StringUtil.isNotEmpty(start)) {
            criteria.andGreaterThanOrEqualTo("startTime",start);
        }
        if (StringUtil.isNotEmpty(end)) {
            criteria.andLessThanOrEqualTo("startTime",end);
        }
        if (StringUtil.isNotEmpty(ownerName)) {
            criteria.andLike("ownerName","%"+ownerName+"%");
        }
        if (num !=null) {
            pageNum = num;
        }
        if (size != null) {
            pageSize = size;
        }
        PageHelper.startPage(pageNum,pageSize);
        Page<Repair> repairs = (Page<Repair>)repairMapper.selectByExample(example);
        return repairs;
    }

    @Override
    public Boolean add(Repair repair) {
        int insert = repairMapper.insert(repair);
        return insert > 0;
    }

    @Override
    public Repair findById(Integer id) {
        return repairMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Repair repair) {
        int i = repairMapper.updateByPrimaryKey(repair);
        return i > 0;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            repairMapper.deleteByPrimaryKey(id);
        }
        return true;
    }
}
