package com.cyou.wechat_longmarch_server.entity.requestBody;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CombinedUserInfo {
    String code;
    String encryptedData;
    String iv;
    String realName;
    Long phoneNumber;
    String nickName;
    String avatarUrl;
}
