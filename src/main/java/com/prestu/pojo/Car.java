package com.prestu.pojo;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 范成恒
 */
@Table(name = "tb_car")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Car {
    @Id
    private Integer id;
    private String owner;
    private String brand;
    private String color;
    private String carNumber;
    private String remark;
    private String createTime;
    private String telephone;
}
