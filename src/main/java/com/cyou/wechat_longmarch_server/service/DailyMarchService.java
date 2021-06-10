package com.cyou.wechat_longmarch_server.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyou.wechat_longmarch_server.entity.table.DailyMarch;
import com.cyou.wechat_longmarch_server.mapper.DailyMarchMapper;
import com.cyou.wechat_longmarch_server.utils.DateUtil;
import com.cyou.wechat_longmarch_server.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class DailyMarchService {
    @Autowired
    DailyMarchMapper dailyMarchMapper;

    @Autowired
    DateUtil dateUtil;

    public Long saveOrUpdate(JSONObject decodeResult,String userId){
        JSONArray stepInfos = decodeResult.getJSONArray("stepInfoList");
        JSONObject latestDayStepInfo = stepInfos.getJSONObject(stepInfos.size()-1);
        Timestamp latestDayTimestamp = latestDayStepInfo.getTimestamp("timestamp");
        Long steps = latestDayStepInfo.getLong("step");

        String latestDayDate = dateUtil.transferTimestamp2Date(latestDayTimestamp.getTime());
        String toDayDate = dateUtil.transferTimestamp2Date(new Date().getTime());

        if(!toDayDate.equals(latestDayDate)){
            return 0L;
        }

        DailyMarch dailyMarch = new DailyMarch();
        dailyMarch.setCreateDate(toDayDate);
        dailyMarch.setUserId(userId);
        dailyMarch.setSteps(steps);

        List<DailyMarch> dailyMarches = dailyMarchMapper.queryByUserIdAndCreateDate(dailyMarch.getUserId(),dailyMarch.getCreateDate());
        if(dailyMarches.size()==0){
            dailyMarch.setId(UUIDGenerator.getUUID());
            dailyMarchMapper.save(dailyMarch);
            return steps;
        }else {
            Long oldSteps= dailyMarches.get(0).getSteps();
            dailyMarchMapper.updateByUserIdAndCreateDate(dailyMarch);
            return steps - oldSteps;
        }
    }


}
