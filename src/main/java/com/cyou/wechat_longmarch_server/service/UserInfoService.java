package com.cyou.wechat_longmarch_server.service;

import com.cyou.wechat_longmarch_server.entity.table.UserInfo;
import com.cyou.wechat_longmarch_server.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;


    public void saveOrUpdate(UserInfo userInfo){
        int size = userInfoMapper.queryById(userInfo.getId()).size();
        if(size==0){
            userInfoMapper.save(userInfo);
        }else {
            userInfoMapper.updateById(userInfo);
        }

    }
}
