package com.prestu.mapper;

import com.prestu.pojo.Car;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CarMapper extends Mapper<Car> {
}
