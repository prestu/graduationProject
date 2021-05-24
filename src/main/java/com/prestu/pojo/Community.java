package com.prestu.pojo;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 范成恒
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_community")
public class Community implements Serializable {
    @Id
    private Integer id;
    private String code;
    private String name;
    private String address;
    private Double area;
    private Integer totalBuildings;
    private Integer totalHouseholds;
    private Integer greeningRate;
    private String developer;
    private String estateCompany;
    private String createTime;
}
