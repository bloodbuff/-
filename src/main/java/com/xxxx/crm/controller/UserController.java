package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.exceptions.ParamsException;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.vo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController  extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping ("login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd) {
        ResultInfo resultInfo = new ResultInfo();

        //to catch捕获service层异常
        try {
            //调用方法
            //设置resultinfo的值，将数据返回给请求
            UserModel userModel = userService.loginCheck(userName, userPwd);
            resultInfo.setResult(userModel);

        } catch (ParamsException e) {
            e.printStackTrace();
            resultInfo.setCode(400);
            resultInfo.setMsg(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setCode(500);
            resultInfo.setMsg("登录失败");
        }
        return resultInfo;
    }
}



