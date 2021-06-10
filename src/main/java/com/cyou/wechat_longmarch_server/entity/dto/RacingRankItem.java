package com.cyou.wechat_longmarch_server.entity.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;


@Data
@ToString
public class RacingRankItem {
    Long ranking;
    String nickName;
    String avatarUrl;
    Long dayCount;
    Timestamp completionTime;
}
