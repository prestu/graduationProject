package com.prestu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.prestu.mapper.ParkingMapper;
import com.prestu.pojo.Parking;
import com.prestu.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    ParkingMapper parkingMapper;
    @Override
    public List<Parking> findAll() {
        return parkingMapper.selectAll();
    }

    @Override
    public Page<Parking> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Parking.class);//指定查询的表tb_car
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 10;
        String sub = "00:00:00";
        String pre = "23:59:59";
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            String start = ((String) searchMap.get("startTime"));
            String end = (String) searchMap.get("endTime");

            String position = (String) searchMap.get("position");
            Integer num = (Integer) searchMap.get("pageNum");
            Integer size = (Integer) searchMap.get("pageSize");
            if (!"".equals(start)) {
                start =start.substring(0,11)+sub;
            }
            if (!"".equals(end)) {
                end =end.substring(0,11)+pre;
            }
            if (StringUtil.isNotEmpty(start)) {
                criteria.andGreaterThanOrEqualTo("createTime", start);
            }
            if (StringUtil.isNotEmpty(end)) {
                criteria.andLessThanOrEqualTo("createTime", end);
            }
            //名称模糊搜索
            if (StringUtil.isNotEmpty(position)) {
                criteria.andLike("position", "%" + position + "%");
            }
            if (num != null) {
                pageNum = num;
            }
            if (size != null) {
                pageSize = size;
            }
        }
        System.out.println(pageNum+","+pageSize);
        //使用PageHelper插件分页
        // pageNum:当前页码
        //pageSzie：当前页数量
        PageHelper.startPage(pageNum, pageSize);
        Page<Parking> parkings = (Page<Parking>) parkingMapper.selectByExample(example);
        return parkings;
    }

    @Override
    public Boolean add(Parking parking) {
        int insert = parkingMapper.insert(parking);
        return insert > 0;
    }

    @Override
    public Parking findById(Integer id) {
        Parking parking = parkingMapper.selectByPrimaryKey(id);
        return parking;
    }

    @Override
    public Boolean update(Parking parking) {
        int i = parkingMapper.updateByPrimaryKeySelective(parking);
        return i>0;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            int i = parkingMapper.deleteByPrimaryKey(id);
            if (i<0) {
                return false;
            }
        }
        return true;
    }
}
