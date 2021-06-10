package com.cyou.wechat_longmarch_server.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class DateUtil {

    public static String transferTimestamp2Date(Long timestamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(timestamp);
    }
}
