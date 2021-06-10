package com.cyou.wechat_longmarch_server.service;
import com.alibaba.fastjson.JSONObject;
import com.cyou.wechat_longmarch_server.config.WeiXinConfig;
import com.cyou.wechat_longmarch_server.utils.DecodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Service
public class DecryptionService {

    @Autowired
    private DecodeUtil decodeUtil;

    @Resource
    private RestTemplate restTemplate;

    public JSONObject getSessionKeyAndOpenid(String code){
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ URLEncoder.encode(WeiXinConfig.AppID,"UTF-8")+"&secret="+URLEncoder.encode(WeiXinConfig.AppSecret,"UTF-8")+"&js_code="+URLEncoder.encode(code,"UTF-8")+"&grant_type=authorization_code";
            URI uri = URI.create(url);
            String jsonString = restTemplate.getForObject(uri,String.class);
            return  JSONObject.parseObject(jsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject decode(String encryptedData, String iv, String sessionKey){
        return decodeUtil.decodeEncryptedData(encryptedData,iv,sessionKey);
    }
}
