package com.prestu.pojo;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author 范成恒
 */
@Table(name="tb_parking")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Parking{
    @Id
    private Integer id;
    private String code;
    private String communityName;
    private String position;
    private String telephone;
    private String carNumber;
    private String ownerName;
    private String status;
    private String createTime;

}
