package com.prestu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.prestu.mapper.PersonMapper;
import com.prestu.pojo.Person;
import com.prestu.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;
    @Override
    public Page<Person> search(Map searchMap) {
        //通用Mapper多条件搜索，标准写法
        Example example = new Example(Person.class);//指定查询的表tb_community
        //1.初始化分页条件
        int pageNum = 1;
        int pageSize = 2;
        if (searchMap != null) {
            Example.Criteria criteria = example.createCriteria();//创建查询条件
            //时间区间
            String start = (String) searchMap.get("startTime");
            String end = (String) searchMap.get("endTime");
            String name = (String) searchMap.get("username");
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
                criteria.andLike("username", "%" + name + "%");
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
        Page<Person> people = (Page<Person>)personMapper.selectByExample(example);
        return people;
    }

    @Override
    public Person searchByName(String username) {
        Example example = new Example(Person.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("username",username);
        Person person = personMapper.selectOneByExample(example);
        return person;
    }
    @Override
    public List<Person> findAll() {

        List<Person> persons = personMapper.selectAll();
        return persons;
    }
    @Override
    public Boolean add(Person person) {
        int insert = personMapper.insert(person);
        return insert > 0;
    }

    @Override
    public Person findById(Integer id) {
        return personMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean update(Person person) {
        int row = personMapper.updateByPrimaryKeySelective(person);
        return row > 0;
    }
    @Override
    public Boolean del(List<Integer> ids) {
        for (Integer id : ids) {
            personMapper.deleteByPrimaryKey(id);
        }
        return true;
    }
}
