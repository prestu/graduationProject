package com.prestu.pojo;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Table(name="tb_parking")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Parking implements Serializable {
    @Id
    private Integer id;
    private String code;
    private String communityName;
    private String position;
    private String telephone;
    private String carNumber;
    private String ownerName;
    private String status;
    private String startTime;
    private String createTime;

}
