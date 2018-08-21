package com.training.service.impl;

import com.training.common.service.CommonService;
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

    @Override
    public boolean queryByNameAndPassword(String name, String password) {
        boolean exist = false;
        String newPassword = DigestUtils.md5Hex(password);
        UserModel userModel = userDao.queryByNameAndPassword(name, newPassword);
        if(userModel == null){
            return exist;
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
}
