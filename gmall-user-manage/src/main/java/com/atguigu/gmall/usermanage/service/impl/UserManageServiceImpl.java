package com.atguigu.gmall.usermanage.service.impl;

import com.atguigu.gmall.usermanage.bean.UserInfo;
import com.atguigu.gmall.usermanage.mapper.UserInfoMapper;
import com.atguigu.gmall.usermanage.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    UserInfoMapper userInfoMapper;

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
}
