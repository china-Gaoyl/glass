package com.glass.common.base;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class BaseController {


    /**
     * 参数校验异常处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult paramsVerifyException(MethodArgumentNotValidException e) {

        ResponseResult result = new ResponseResult();
        result.setFailCode(ResponseCode.COMMON_PRI_FIELD_EMPTY);
        result.setData(e.getBindingResult().getFieldError().getDefaultMessage());

        return result;
    }

    /**
     * 主键冲突异常处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseResult exception(SQLIntegrityConstraintViolationException e) {
        ResponseResult result = new ResponseResult();
        result.setCode(String.valueOf(e.getErrorCode()));
        result.setMsg(e.getMessage());
        result.setData("唯一主键冲突");
        return result;
    }

    public ResponseResult success() {
        ResponseResult result = new ResponseResult<>();
        result.setDefSuc();
        return result;
    }

}
