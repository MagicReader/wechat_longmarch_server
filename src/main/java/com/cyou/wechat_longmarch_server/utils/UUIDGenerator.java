package com.cyou.wechat_longmarch_server.utils;

import java.util.UUID;

public class UUIDGenerator {

    /**
     * 获得一个UUID
     */
    public static String getUUID() {
        String uuid  = UUID.randomUUID().toString();
        // 去掉“-”符号
        return uuid .replaceAll("-", "");
    }

    /**
     * 获得指定数目的UUID
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
