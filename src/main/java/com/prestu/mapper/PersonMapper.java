package com.prestu.mapper;

import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import com.prestu.pojo.Person;
@Repository
public interface PersonMapper extends Mapper<Person> {
}
