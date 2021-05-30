package com.prestu.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 范成恒
 */
@Data
@Table(name = "tb_activity")
public class Activity {
    @Id
    private Integer id;
    private String communityName;
    private String communityId;
    private String title;
    private String address;
    private String organizer;
    private String startTime;
    private String endTime;
    private String createTime;
}
