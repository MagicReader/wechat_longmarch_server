package com.cyou.wechat_longmarch_server.entity.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class AccumulativeRank {
    ArrayList<AccumulativeRankItem> rankingList;
    Long myRanking;
    Long pageSize;
    Long pagination;
}
