package com.training.convert.impl;

import com.training.convert.Convert;
import com.training.model.UserModel;
import com.training.model.form.UserForm;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class UserReverseConvert implements Convert<UserForm, UserModel> {
    @Override
    public UserModel createTarget() {
        return new UserModel();
    }

    @Override
    public UserModel convert(UserForm form) {
        UserModel model = createTarget();
        model.setId(form.getId());
        model.setName(form.getName());
        String password = form.getPassword();
        String newPassword = DigestUtils.md5Hex(password);
        model.setPassword(newPassword);
        model.setCreateDate(new Date());
        model.setModifyDate(new Date());
        model.setIsValid(form.getIsValid());
        return model;
    }
}
