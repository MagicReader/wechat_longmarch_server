package com.cyou.wechat_longmarch_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/march")
public class MarchController {

    @GetMapping("/marchInfo")
    public @ResponseBody
    Object queryMarchInfo(@RequestAttribute("userId") String userId) {

        return null;
    }

    @GetMapping("/accumulativeRank")
    public @ResponseBody
    Object queryCurrentAccumulativeRank(@RequestAttribute("userId") String userId, @RequestParam("PageSize") Long PageSize, @RequestParam("pagination") Long pagination) {
        return null;
    }

    @GetMapping("/racingRank")
    public @ResponseBody
    Object queryCurrentRacingRank(@RequestAttribute("userId") String userId,@RequestParam("PageSize") Long PageSize,@RequestParam("pagination") Long pagination) {
        return null;
    }

}
