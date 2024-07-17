package com.mirror.mapper;

import com.mirror.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mirror.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-07-16 22:26:24
* @Entity com.mirror.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    String Login(String userName);

    int register(User user);

    UserVo queryInfo(String userName);

    int queryUidByName(String userName);

    String getNameById(Integer userId);
}




