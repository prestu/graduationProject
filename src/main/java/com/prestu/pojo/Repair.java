package com.prestu.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 范成恒
 */

@Data
@Table(name="tb_repair")
public class Repair {
    @Id
    private Integer id;
    private String communityName;
    private String ownerName;
    private String deviceName;
    private String description;
    private String createTime;
    private String status;
}
