package com.cyou.wechat_longmarch_server.service;

import com.cyou.wechat_longmarch_server.config.WeiXinConfig;
import com.cyou.wechat_longmarch_server.entity.table.DailyMarch;
import com.cyou.wechat_longmarch_server.entity.table.RacingLeaderboard;
import com.cyou.wechat_longmarch_server.mapper.DailyMarchMapper;
import com.cyou.wechat_longmarch_server.mapper.RacingLeaderBoardMapper;
import com.cyou.wechat_longmarch_server.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacingLeaderBoardService {

    @Autowired
    DailyMarchMapper dailyMarchMapper;

    @Autowired
    RacingLeaderBoardMapper racingLeaderBoardMapper;

    public boolean resolveRacingLeaderBoard(String userId){
        List<RacingLeaderboard> racingLeaderboards = racingLeaderBoardMapper.queryAllByUserId(userId);
        if(racingLeaderboards.size()>0){ // 已在竞速榜上
            return true;
        }

        List<DailyMarch> dailyMarchList = dailyMarchMapper.queryAllByUserId(userId);
        Long totalSteps = 0L;
        for(DailyMarch item:dailyMarchList){
            totalSteps += item.getSteps();
        }

        if(WeiXinConfig.TheLongMarchSteps < totalSteps){
            RacingLeaderboard racingLeaderboard = new RacingLeaderboard();
            racingLeaderboard.setId(UUIDGenerator.getUUID());
            racingLeaderboard.setUserId(userId);
            racingLeaderboard.setDayCount(new Long(dailyMarchList.size()));
            racingLeaderBoardMapper.save(racingLeaderboard);
        }

        return false;
    }
}
