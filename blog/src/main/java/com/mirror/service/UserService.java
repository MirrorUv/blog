package com.mirror.service;

import com.mirror.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mirror.vo.LoginVo;
import com.mirror.vo.UserVo;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2024-07-16 22:26:24
*/
public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param userVo
     * @return 注册成功
     */
    boolean register(UserVo userVo);

    /**
     * 登录
     * @param loginVo
     * @return token
     */
    String doLogin(LoginVo loginVo);

    /**
     * 查询信息
     * @return
     */
    UserVo queryInfo();
}
