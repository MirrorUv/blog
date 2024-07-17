package com.mirror.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mirror.pojo.User;
import com.mirror.service.UserService;
import com.mirror.mapper.UserMapper;
import com.mirror.utils.AuthenticationUtil;
import com.mirror.utils.BCryptUtil;
import com.mirror.utils.JwtUtil;
import com.mirror.vo.LoginVo;
import com.mirror.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-07-16 22:26:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private  UserMapper userMapper;
    @Override
    public boolean register(UserVo userVo) {
        User user = User.builder().username(userVo.getUsername()).password(BCryptUtil.enCoding(userVo.getPassword())).email(userVo.getEmail()).build();
        int res = userMapper.register(user);
        return res > 0;
    }

    @Override
    public String doLogin(LoginVo loginVo) {
        String password = userMapper.Login(loginVo.getUsername());
        boolean res = BCryptUtil.verify(loginVo.getPassword(),password);
        if(!res){
            return null;
        }
        return JwtUtil.CreateToken(loginVo.getUsername());
    }

    @Override
    public UserVo queryInfo() {
        String userName = AuthenticationUtil.getUser();
        return userMapper.queryInfo(userName);
    }
}




