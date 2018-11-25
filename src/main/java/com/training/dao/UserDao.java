package com.training.dao;

import com.training.model.UserModel;

public interface UserDao {

    UserModel queryByNameAndPassword(String name, String password);

    UserModel queryByMobile(String mobile);
}
