package com.prestu.pojo;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "tb_owner")
public class Person {
    @Id
    private Integer id;
    private String communityName;
    private String houseAddress;
    private String username;
    private String idcard;
    private String telephone;
    private String profession;
    private String sex;
    private String createTime;
    private String password;
}
