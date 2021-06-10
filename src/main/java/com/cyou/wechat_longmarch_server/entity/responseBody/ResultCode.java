package com.cyou.wechat_longmarch_server.entity.responseBody;

public enum  ResultCode {
    SUCCESS(200,"success"),
    FAILED(500,"failed"),
    VALIDATE_FAILED(404,"参数校验失败"),
    UNAUTHORIZED(401,"暂未登陆或token已经过期"),
    FORBIDDEN(403,"没有相关权限");

    private final long code;
    private final String msg;

    ResultCode(long code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public long getCode(){ return code;}
    public String getMsg(){ return msg;}

}
