package com.cyou.wechat_longmarch_server.entity.responseBody;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommonResult<T>  {
    Long code;
    String msg;
    T data;

    public CommonResult(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}

