package com.cyou.wechat_longmarch_server.entity.table;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class RacingLeaderboard {
    String id;
    String userId;
    Long dayCount;
    Timestamp createTime;
    Timestamp updateTime;
}
