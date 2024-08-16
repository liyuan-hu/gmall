package com.atguigu.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.UserManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Reference
    UserManageService userManageService;

    @ResponseBody
    @RequestMapping(value = "initOrder")
    public String initOrder(HttpServletRequest request){
        String userid = request.getParameter("userid");
        List<UserAddress> userAddressList = userManageService.getUserAddressList(userid);
        return JSON.toJSONString(userAddressList);
    }
}
