package com.cyou.wechat_longmarch_server.mapper;

import com.cyou.wechat_longmarch_server.entity.table.RacingLeaderboard;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacingLeaderBoardMapper {
    List<RacingLeaderboard> queryAllByUserId(String userId);
    void save(RacingLeaderboard racingLeaderboard);
}
