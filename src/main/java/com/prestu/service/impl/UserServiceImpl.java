package com.prestu.service.impl;

import com.prestu.service.UserService;
import com.prestu.mapper.UserMapper;
import com.prestu.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public boolean add(User user) {
        int insert = userMapper.insertSelective(user);
        return insert > 0;
    }

    @Override
    public User findByName(String name) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("username",name);
        User user = userMapper.selectOneByExample(example);
        return user;
    }

    @Override
    public boolean update(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i > 0;
    }

}
