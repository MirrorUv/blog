package com.mirror.common;



public enum ResultEnum {
    //通用
    SUCCESS(200,"操作成功"),
    ERROR(400,"操作失败"),
    CREATE_ARTICLE_ERROR(5001,"创建文章失败"),
    UPDATE_ARTICLE_ERROR(5002,"更新文章失败"),
    DELETE_ARTICLE_ERROR(5003,"删除文章失败"),
    QUERY_ARTICLE_ERROR(5004,"查询文章失败"),
    //未登录异常
    USER_NOT_LOGIN(5000,"请先登录"),
    //注册异常
    USERNAME_EXIST_ERROR(5005,"用户名已经存在"),
    //token异常
    TOKEN_EXPIRE(5006,"身份认证失败，请重新登录"),

    ;
    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
