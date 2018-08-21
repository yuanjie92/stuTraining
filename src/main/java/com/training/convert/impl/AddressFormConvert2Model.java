package com.training.convert.impl;

import com.training.convert.Convert;
import com.training.model.AddressModel;
import com.training.model.form.AddressForm;

public class AddressFormConvert2Model implements Convert<AddressForm, AddressModel> {
    @Override
    public AddressModel createTarget() {
        return new AddressModel();
    }

    @Override
    public AddressModel convert(AddressForm form) {
        AddressModel model = new AddressModel();
        model.setAddrName(form.getAddrName());
        model.setAreaCode(form.getAreaCode());
        model.setAreaName(form.getAreaName());
        model.setCityCode(form.getCityCode());
        model.setCityName(form.getCityName());
        model.setProvCode(form.getProvCode());
        model.setProvName(form.getProvName());
        return model;
    }
}
