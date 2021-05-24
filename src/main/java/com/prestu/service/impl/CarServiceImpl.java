package com.prestu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.prestu.mapper.CarMapper;
import com.prestu.pojo.Car;
import com.prestu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;
    @Override
    public List<Car> findAll() {

        List<Car> cars = carMapper.selectAll();
        return cars;
    }

    @Override
    public Page<Car> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Car.class);//指定查询的表tb_car
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 2;
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            String start = (String) searchMap.get("startTime");
            String end = (String) searchMap.get("endTime");
            String name = (String) searchMap.get("name");
            Integer num = (Integer) searchMap.get("pageNum");
            Integer size = (Integer) searchMap.get("pageSize");
            if (StringUtil.isNotEmpty(start)) {
                criteria.andGreaterThanOrEqualTo("createTime", start);
            }
            if (StringUtil.isNotEmpty(end)) {
                criteria.andLessThanOrEqualTo("createTime", end);
            }
            //名称模糊搜索
            if (StringUtil.isNotEmpty(name)) {
                criteria.andLike("name", "%" + name + "%");
            }
            if (num != null) {
                pageNum = num;
            }
            if (size != null) {
                pageSize = size;
            }
        }
        //使用PageHelper插件分页
        // pageNum:当前页码
        //pageSzie：当前页数量
        PageHelper.startPage(pageNum, pageSize);
        Page<Car> cars = (Page<Car>) carMapper.selectByExample(example);
        return cars;
    }
    @Override
    public Boolean add(Car car) {
        int insert = carMapper.insert(car);
        return insert > 0;
    }

    @Override
    public Car findById(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Car car) {
        int row = carMapper.updateByPrimaryKeySelective(car);
        return row > 0;
    }
    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            carMapper.deleteByPrimaryKey(id);
        }
        return true;
    }

}
