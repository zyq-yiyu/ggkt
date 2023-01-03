package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/vod/user")
@CrossOrigin //跨域
public class LoginController {


    //
    @PostMapping("login")
    public Result login(){
        Map<String,Object> map =new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }
}
