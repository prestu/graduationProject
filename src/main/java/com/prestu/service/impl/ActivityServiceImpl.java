package com.prestu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.prestu.mapper.ActivityMapper;
import com.prestu.pojo.Activity;
import com.prestu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @author 范成恒
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public List<Activity> findAll() {
        return activityMapper.selectAll();
    }

    @Override
    public Page<Activity> search(Map searchMap) {
        int pageNum=  1;
        int pageSize = 10;
        String sub = "00:00:00";
        String pre = "23:59:59";
        Example example = new Example(Activity.class);
        if (searchMap != null) {

            Example.Criteria criteria = example.createCriteria();
            String title = (String) searchMap.get("title");
            String start = (String) searchMap.get("startTime");
            String end = (String) searchMap.get("endTime");
            Integer num = (Integer) searchMap.get("pageNum");
            Integer size = (Integer) searchMap.get("pageSize");
            System.out.println("title:" + title);
            System.out.println("start:" + start);
            System.out.println("end:" + end);
            System.out.println("num:" + num);
            System.out.println("size:" + size);
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
            if (title != null) {
                criteria.andLike("title", "%" + title + "%");
            }
            if (num != null) {
                pageNum = num;
            }
            if (size != null) {
                pageSize = size;
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        Page<Activity> activities = (Page<Activity>)activityMapper.selectByExample(example);
        for (Activity activity : activities) {
            System.out.println(activity);
        }
        System.out.println(activities);
        return activities;
    }

    @Override
    public boolean add(Activity activity) {
        int insert = activityMapper.insert(activity);
        return insert > 0;
    }

    @Override
    public Activity findById(Integer id) {
        Activity activity = activityMapper.selectByPrimaryKey(id);
        return activity;
    }

    @Override
    public Boolean update(Activity activity) {
        int i = activityMapper.updateByPrimaryKeySelective(activity);
        return i > 0;
    }

    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            int i = activityMapper.deleteByPrimaryKey(id);
            if (i <= 0) {
                return false;
            }
        }
        return true;
    }
}
