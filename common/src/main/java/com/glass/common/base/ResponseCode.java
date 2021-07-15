package com.glass.common.base;

import java.util.Optional;

/**
 * @ClassDesc 状态码枚举类，自定义添加
 */
public enum ResponseCode {

    //默认返回信息
    DEFAULT_SUCCESS("00000", "请求成功"),
    DEFAULT_FAIL("99999", "请求失败"),

    //运行时异常
    EXCEPTION_SERVICE_ERROR("10000", "服务异常"),

    //参数校验
    COMMON_PRI_FIELD_EMPTY("20001", "必要字段为空"),
    DATA_BATCH_INPUT_ERROR("20010", "批量导入数据格式错误"),
    DATA_CHECK_INVOICE_EXIST_ERROR("20011", "发票号已存在错误"),

    //权限相关
    LOGIN_SUCCESS("91001", "登录成功"),
    LOGOUT_SUCCESS("91002", "登出成功"),
    USER_OTHERS_LOGIN("91003", "账号下线"),
    LOGIN_TO_LOGIN("91004", "请登录"),
    NOT_SAME("91005", "两次密码不一致"),
    ORIGINAL_PASSWORD_ERROR("91006", "原密码不正确"),
    LOGIN_USERNAME_NOT_FOUND_ERROR("90001", "账户不存在"),
    LOGIN_USERNAME_OR_PASSWORD_ERROR("90002", "账户或密码错误"),
    LOGIN_LOCKED_ERROR("90003", "账户已锁定"),
    LOGIN_CREDENTIALS_EXPIRED_ERROR("90004", "证书已到期"),
    LOGIN_ACCOUNT_EXPIRED_ERROR("90005", "账户已过期"),
    LOGIN_DISABLED_ERROR("90006", "账户已禁用"),
    LOGIN_FAIL_ERROR("90007", "登陆失败"),
    NOT_LOGIN_ERROR("90008", "用户未登录，请登录"),
    NO_PERMISSION("91000", "没有权限，请联系管理员"),
    ;



    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据代码获取枚举名称
     */
    public static String getMsgByCode(String code) {
        /*
        所以如何获取Optional对象？—>使用of(T value)和ofNullable(T value)方法
        of(T value)方法中value是任意类型但是不能为null；
        ofNullable(T value)方法value也是任意类型，但是可以为null；
          */
        Optional<Object> optional = Optional.of(null);
        Object o = optional.get();
        //Optional.ofNullable();

        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode.getMsg();
            }
        }
        return null;
    }

    /**
     * 根据名称获取枚举代码
     */
    public static String getCodeByMsg(String msg) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getMsg().equals(msg)) {
                return responseCode.getCode();
            }
        }
        return null;
    }

    /**
     * 根据代码获取枚举对象
     */
    public static ResponseCode getErrorCodeEnumByCode(String code) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getCode().equals(code)) {
                return responseCode;
            }
        }
        return null;
    }

    /**
     * 根据名称获取枚举对象
     */
    public static ResponseCode getErrorCodeEnumByMsg(String msg) {
        for (ResponseCode responseCode : ResponseCode.values()) {
            if (responseCode.getMsg().equals(msg)) {
                return responseCode;
            }
        }
        return null;
    }


}
