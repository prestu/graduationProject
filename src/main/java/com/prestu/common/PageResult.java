package com.prestu.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 范成恒
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResult implements Serializable {
    private static final long serialVersionUID = -6250130355901431732L;

    //是否成功
    private boolean flag;
    //状态码
    private Integer code;
    //返回消息
    private String message;
    //结果集
    private Object data;
    //总记录数
    private Long total;
}
