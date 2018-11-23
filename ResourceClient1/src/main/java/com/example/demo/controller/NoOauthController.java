package com.example.demo.controller;

import com.example.demo.common.APIResult;
import com.example.demo.common.HTTPCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHOR huxl
 * @DATE 2018/11/22 18:16
 * @DES
 */
@RestController
public class NoOauthController {

    @RequestMapping("/noOauth")
    @ResponseBody
    public APIResult noOauth(@RequestParam(name="name") String name){
        return new APIResult(HTTPCode.OK,"This is noOauth URI,my name is "+name);
    }
}
