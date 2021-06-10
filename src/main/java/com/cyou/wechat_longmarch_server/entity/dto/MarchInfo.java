package com.cyou.wechat_longmarch_server.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MarchInfo {
    Long dayCount;
    Long orderNum;
    Double distanceCount;
    Long myRanking;
}
