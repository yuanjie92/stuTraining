package com.training.dao.impl;

import com.training.common.dao.CommonDao;
import com.training.dao.UserDao;
import com.training.model.StudentModel;
import com.training.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    @Autowired
    private CommonDao commonDao;

    @Override
    public UserModel queryByNameAndPassword(String name, String password) {
        UserModel userModel = null;
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        List<UserModel> list = commonDao.getEntitiesByFields(UserModel.class, map);
        if(!list.isEmpty() && list.size() > 0){
            userModel = list.get(0);
        }
        return userModel;
    }
}
