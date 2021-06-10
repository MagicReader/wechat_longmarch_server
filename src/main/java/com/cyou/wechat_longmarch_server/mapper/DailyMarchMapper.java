package com.cyou.wechat_longmarch_server.mapper;

import com.cyou.wechat_longmarch_server.entity.table.DailyMarch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyMarchMapper {
    void save(DailyMarch dailyMarch);
    void updateByUserIdAndCreateDate(DailyMarch dailyMarch);
    List<DailyMarch> queryByUserIdAndCreateDate(String userId,String createDate);
    List<DailyMarch> queryAllByUserId(String userId);
}
