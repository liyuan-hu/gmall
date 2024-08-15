package com.atguigu.gmall.usermanage.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.mapper.UserAddressMapper;
import com.atguigu.gmall.mapper.UserInfoMapper;
import com.atguigu.gmall.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserAddressMapper userAddressMapper;

    @Override
    public List<UserInfo> getUserInfoList(UserInfo userInfoQuery) {
        List<UserInfo> userInfos = null;
        Example example = new Example(UserInfo.class);
        example.createCriteria().andLike("loginName", "%" + userInfoQuery.getLoginName() + "%");
        userInfos = userInfoMapper.selectByExample(example);
        return userInfos;
    }

    @Override
    public UserInfo getUserInfo(UserInfo userInfoQuery) {
        UserInfo userInfo = null;
        userInfo = userInfoMapper.selectOne(userInfoQuery);
        return userInfo;
    }

    @Override
    public void delete(UserInfo userInfoQuery) {
        userInfoMapper.deleteByPrimaryKey(userInfoQuery.getId());
    }

    @Override
    public void addUserInfo(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        Example example = new Example(UserInfo.class);
        example.createCriteria().andLike("loginName", "%" + userInfo.getLoginName() + "%");
        userInfo.setLoginName(null);
        userInfoMapper.updateByExampleSelective(userInfo, example);
    }

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        List<UserAddress> addressList = null;
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        addressList = userAddressMapper.select(userAddress);
        return addressList;
    }
}
