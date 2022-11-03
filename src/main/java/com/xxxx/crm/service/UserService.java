package com.xxxx.crm.service;

import com.xxxx.crm.vo.UserModel;
import com.xxxx.crm.base.BaseService;
import com.xxxx.crm.dao.UserMapper;
import com.xxxx.crm.utils.AssertUtil;
import com.xxxx.crm.utils.Md5Util;
import com.xxxx.crm.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends BaseService<User,Integer> {

    @Resource
    private UserMapper userMapper;


    public UserModel loginCheck(String userName, String userPwd) {

        //2参数判断，用户名，密码
        checkLoginData(userName, userPwd);
        //调用数据访问层，查询用户记录，返回用户对象
        User user = userMapper.queryUserByName(userName);
        //判断账号是否存在
        AssertUtil.isTrue(user == null, "用户不在");
        //校验前台传来的密码是否正确
        checkLoginPwd(user.getUserPwd(), userPwd);

        //构建用户对象
         return  buildUserInfo(user);

    }

    private UserModel buildUserInfo(User user) {
        UserModel userModel=new UserModel();
        userModel.setUserId(user.getId());
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());

        return userModel;
    }

    //先加密，后比较
    //前端写入登录记录
    private void checkLoginPwd(String userPwd,String Pwd){
        //加密
       Pwd = Md5Util.encode(Pwd);
        //是否相等判断
        AssertUtil.isTrue(!userPwd.equals(Pwd),"用户密码错误");
    }

    //1参数非空校验
    private void checkLoginData(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用密码不能为空");
    }

}
