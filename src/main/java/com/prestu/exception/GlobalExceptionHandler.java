package com.prestu.exception;

import com.prestu.common.MessageConstant;
import com.prestu.common.Result;
import com.prestu.common.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author 范成恒
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public Result error(Exception e) {
        Result result = new Result(false, StatusCode.ERROR, MessageConstant.SYSTEM_BUSY);
        return result;
    }
}
