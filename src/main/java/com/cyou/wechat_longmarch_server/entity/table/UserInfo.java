package com.cyou.wechat_longmarch_server.entity.table;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class UserInfo {
    String id;
    String realName;
    Long phoneNumber;
    String nickName;
    String avatarUrl;
    Timestamp createTime;
    Timestamp updateTime;
}
