package com.example.demo.controller;

import com.example.demo.common.APIResult;
import com.example.demo.common.HTTPCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @AUTHOR huxl
 * @DATE 2018/11/22 18:25
 * @DES
 */
@RestController
public class TouristController {

    @PreAuthorize("hasRole('TOURIST')")
    @RequestMapping("/tourist")
    @ResponseBody
    public APIResult admin(@RequestParam(name="name") String name){
        return new APIResult(HTTPCode.OK,"This is tourist URI,my name is "+name);
    }
}
