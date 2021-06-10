package com.cyou.wechat_longmarch_server.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AccumulativeRankItem {
    Long ranking;
    String nickName;
    String avatarUrl;
    Long dayCount;
    Double distanceCount;
}
