package com.cyou.wechat_longmarch_server.entity.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MarchRouteItem {
    Long orderNum;
    String placeName;
    String placeDesc;
    Double longitude;
    Double latitude;
    Double distanceFromBegin;
}
