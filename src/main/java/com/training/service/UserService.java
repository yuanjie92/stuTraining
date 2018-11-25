package com.training.service;

import com.training.model.UserModel;
import com.training.model.form.UserForm;

public interface UserService {
    boolean queryByNameAndPassword(String name, String password);

    void save(UserForm userForm);

    boolean queryByMobile(String mobile);
}
