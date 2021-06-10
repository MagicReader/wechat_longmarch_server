package com.cyou.wechat_longmarch_server.entity.table;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class LongMarchRoute {
    String id;
    Long orderNum;
    String placeName;
    String placeDesc;
    Double longitude;
    Double latitude;
    Double distanceFromBegin;
    Timestamp createTime;
    Timestamp updateTime;
}
