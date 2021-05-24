package com.prestu.pojo;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User implements Serializable {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String email;
    private String number;

}
