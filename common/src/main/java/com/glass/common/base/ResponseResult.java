package com.glass.common.base;

import lombok.Data;

@Data
public class ResponseResult<T> {
    //请求的标识符，0为成功1为失败
    private String code;
    //请求的消息
    private String msg;
    //需要返回的数据
    private T data;

    public ResponseResult() {
        this.setDefSuc();
    }


    //设置默认请求成功
    public void setDefSuc() {
        this.code = ResponseCode.DEFAULT_SUCCESS.getCode();
        this.msg = ResponseCode.DEFAULT_SUCCESS.getMsg();
    }

    //设置默认请求失败
    public void setDefFail() {
        this.code = ResponseCode.DEFAULT_FAIL.getCode();
        this.msg = ResponseCode.DEFAULT_FAIL.getMsg();
    }

    public void setFailCode(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
    }

}
