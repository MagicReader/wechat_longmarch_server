package com.cyou.wechat_longmarch_server.controller;

import com.alibaba.fastjson.JSONObject;
import com.cyou.wechat_longmarch_server.entity.table.UserInfo;
import com.cyou.wechat_longmarch_server.entity.requestBody.CombinedUserInfo;
import com.cyou.wechat_longmarch_server.entity.responseBody.CommonResponseBody;
import com.cyou.wechat_longmarch_server.service.DailyMarchService;
import com.cyou.wechat_longmarch_server.service.DecryptionService;
import com.cyou.wechat_longmarch_server.service.RacingLeaderBoardService;
import com.cyou.wechat_longmarch_server.service.UserInfoService;
import com.cyou.wechat_longmarch_server.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private DecryptionService decryptionService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DailyMarchService dailyMarchService;

    @Autowired
    private RacingLeaderBoardService racingLeaderBoardService;

    @GetMapping("/token")
    public @ResponseBody
    Object resolveCombinedUserInfo(@RequestBody CombinedUserInfo combinedUserInfo) {
        // 校验参数 判空
        if(StringUtils.isEmpty(combinedUserInfo.getCode()) ||
                StringUtils.isEmpty(combinedUserInfo.getEncryptedData()) ||
                StringUtils.isEmpty(combinedUserInfo.getIv()) ||
                StringUtils.isEmpty(combinedUserInfo.getNickName()) ||
                StringUtils.isEmpty(combinedUserInfo.getAvatarUrl())
        ){
            return CommonResponseBody.validateFailed();
        }
        // 利用code 获取openid和session_key,分别作为userId和token
        JSONObject sessionKeyAndOpenidJSONObject = decryptionService.getSessionKeyAndOpenid(combinedUserInfo.getCode());
        String userId = sessionKeyAndOpenidJSONObject.getString("openid");
        String token = sessionKeyAndOpenidJSONObject.getString("session_key");
        // redis缓存<userId,token>
        redisUtil.set(userId,token);
        // 存储或更新用户个人信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(sessionKeyAndOpenidJSONObject.getString("openid"));
        userInfo.setRealName(combinedUserInfo.getRealName());
        userInfo.setPhoneNumber(combinedUserInfo.getPhoneNumber());
        userInfo.setNickName(combinedUserInfo.getNickName());
        userInfo.setAvatarUrl(combinedUserInfo.getAvatarUrl());
        userInfoService.saveOrUpdate(userInfo);
        // 解密用户步数加密数据
        JSONObject decodeResult = decryptionService.decode(combinedUserInfo.getEncryptedData(),combinedUserInfo.getIv(),token);
        // 存储或更新用户当天步数,返回当天新增步数
        Long newlyIncreasedTodaySteps = dailyMarchService.saveOrUpdate(decodeResult,userId);
        // 判断用户是否在竞速榜上，若不在竞速榜上 再判断 是否 完成长征路程，是 则存储到竞速榜
        if(newlyIncreasedTodaySteps>0){
            racingLeaderBoardService.resolveRacingLeaderBoard(userId);
        }
        return CommonResponseBody.success(token);
    }
}
