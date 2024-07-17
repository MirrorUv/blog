package com.mirror.controller;

import com.mirror.anno.RequireLogin;
import com.mirror.common.Result;
import com.mirror.common.ResultEnum;
import com.mirror.service.UserService;
import com.mirror.utils.BCryptUtil;
import com.mirror.vo.LoginVo;
import com.mirror.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody @Validated UserVo userVo){

            try {
                boolean res =userService.register(userVo);
                log.info("{} 用户创建成功",userVo.getUsername());
                return Result.<Boolean>builder().code(ResultEnum.SUCCESS.getCode())
                        .message(ResultEnum.SUCCESS.getMessage()).data(res).build();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                log.info("{} 用户名存在",userVo.getUsername());
                return Result.<Boolean>builder().code(ResultEnum.USERNAME_EXIST_ERROR.getCode())
                        .message(ResultEnum.USERNAME_EXIST_ERROR.getMessage()).data(false).build();
            }

        }
    @PostMapping("/login")
    public Result<String> doLogin(@RequestBody @Validated LoginVo loginVo) {
        try {
            String token = userService.doLogin(loginVo);
            if (token == null){
                log.info("{} 用户名或密码错误",loginVo.getUsername());
                return Result.<String>builder().code(ResultEnum.ERROR.getCode())
                        .message(ResultEnum.ERROR.getMessage()).data("用户名或密码错误").build();
            }
            log.info("{} 登录成功",loginVo.getUsername());
            return Result.<String>builder().code(ResultEnum.SUCCESS.getCode())
                    .message(ResultEnum.SUCCESS.getMessage()).data(token).build();
        } catch (Exception e) {
            log.info("{} 用户名或密码错误",loginVo.getUsername());
            return Result.<String>builder().code(ResultEnum.ERROR.getCode())
                    .message(ResultEnum.ERROR.getMessage()).data("用户名或密码错误").build();
        }
    }
    @GetMapping("/me")
    @RequireLogin
    public Result<UserVo> queryInfo(){
        try {
            UserVo userVo = userService.queryInfo();
            return Result.<UserVo>builder().code(ResultEnum.SUCCESS.getCode())
                    .message(ResultEnum.SUCCESS.getMessage()).data(userVo).build();
        } catch (Exception e) {
            log.info("获取信息错误");
            return Result.<UserVo>builder().code(ResultEnum.USER_NOT_LOGIN.getCode())
                    .message(ResultEnum.USER_NOT_LOGIN.getMessage()).data(null).build();
        }

    }
}
