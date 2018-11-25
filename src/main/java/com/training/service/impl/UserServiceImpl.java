package com.training.service.impl;

import com.training.common.service.CommonService;
import com.training.common.service.RedisService;
import com.training.convert.impl.UserReverseConvert;
import com.training.dao.UserDao;
import com.training.model.UserModel;
import com.training.model.form.UserForm;
import com.training.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserReverseConvert userReverseConvert;
    @Autowired
    private CommonService commonService;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean queryByNameAndPassword(String name, String password) {
        boolean exist = false;
        String newPassword = DigestUtils.md5Hex(password);
        UserModel userModel = null;

        userModel = redisService.get("USER_NAME_" + name,UserModel.class);
        if(userModel == null){
            userModel = userDao.queryByNameAndPassword(name, newPassword);
            if(userModel == null){
               return exist;
            }else{
                redisService.set("USER_NAME_" + name,userModel);
            }
        }

        if(userModel.getName().equals(name) && userModel.getPassword().equals(newPassword)){
            exist = true;
        }
        return exist;
    }

    @Override
    public void save(UserForm userForm) {
        UserModel userModel = userReverseConvert.convert(userForm);
        commonService.saveOrUpdateEntity(userModel);
    }

    @Override
    public boolean queryByMobile(String mobile) {
        boolean exist = false;
        UserModel userModel = null;
        userModel = redisService.get("USER_MOBILE_" + mobile,UserModel.class);
        if(userModel == null){
                userModel = userDao.queryByMobile(mobile);
            if(userModel == null){
                return exist;
            }else{
                redisService.set("USER_MOBILE_" + mobile,userModel);
                exist = true;
            }
        }
        return exist;
    }
}
