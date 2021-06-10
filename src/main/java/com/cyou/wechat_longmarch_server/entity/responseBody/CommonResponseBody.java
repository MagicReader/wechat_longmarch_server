package com.cyou.wechat_longmarch_server.entity.responseBody;

public class CommonResponseBody {
    /**
     *成功返回结果
     *@param data 获取的数据
     */
    public static<T> CommonResult<T> success(T data){
        return new CommonResult<T>(
                ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMsg(),
                data);
    }

    /**
     *失败返回结果
     */
    public static <T> CommonResult<T> failed(){
        return new CommonResult<T>(
                ResultCode.FAILED.getCode(),
                ResultCode.FAILED.getMsg(),
                null);
    }

    /**
     *参数校验失败返回结果
     */
    public static <T> CommonResult<T> validateFailed(){
        return new CommonResult<T>(
                ResultCode.VALIDATE_FAILED.getCode(),
                ResultCode.VALIDATE_FAILED.getMsg(),
                null);
    }

    /**
     *没有相关权限
     */
    public static <T> CommonResult<T> forbidden(){
        return new CommonResult<T>(
                ResultCode.FORBIDDEN.getCode(),
                ResultCode.FORBIDDEN.getMsg(),
                null);
    }
}
