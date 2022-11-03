package com.xxxx.crm.dao;

import com.xxxx.crm.base.BaseMapper;
import com.xxxx.crm.vo.User;
import org.apache.ibatis.annotations.Mapper;

public interface UserMapper extends BaseMapper<User,Integer> {

    User queryUserByName(String username);

}