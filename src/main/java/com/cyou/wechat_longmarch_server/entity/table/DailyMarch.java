package com.cyou.wechat_longmarch_server.entity.table;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class DailyMarch {
    String id;
    String userId;
    Long steps;
    Long dayCount;
    String createDate;
    Timestamp createTime;
    Timestamp updateTime;
}
