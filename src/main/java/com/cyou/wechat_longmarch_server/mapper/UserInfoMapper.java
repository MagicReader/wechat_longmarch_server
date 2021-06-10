package com.cyou.wechat_longmarch_server.mapper;

import com.cyou.wechat_longmarch_server.entity.table.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    void save(UserInfo userInfo);
    void updateById(UserInfo userInfo);
    List<UserInfo> queryById(String userId);
}
