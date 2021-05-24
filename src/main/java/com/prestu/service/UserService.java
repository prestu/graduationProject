package com.prestu.service;

import com.prestu.pojo.User;

public interface UserService {
    boolean add(User user);
    User findByName(String name);

    boolean update(User user);
}
