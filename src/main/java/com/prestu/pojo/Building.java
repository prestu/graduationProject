package com.prestu.pojo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 范成恒
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "tb_building")
public class Building implements Serializable {
    @Id
    private Integer id;
    //社区名字
    private String communityName;
    //社区编号
    private Integer communityId;
    //
    private String name;
    private Integer totalHouseHolds;
    private String description;
    private Date createTime;
    private Date updateTime;

}
