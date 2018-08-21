package com.training.convert.impl;

import com.training.convert.Convert;
import com.training.model.AddressModel;
import com.training.model.Data.AddressData;

public class AddressConvert implements Convert<AddressModel, AddressData> {
    @Override
    public AddressData createTarget() {
        return new AddressData();
    }

    @Override
    public AddressData convert(AddressModel model) {
        AddressData addressData = createTarget();
        addressData.setAddrName(model.getAddrName());
        addressData.setAreaCode(model.getAreaCode());
        addressData.setAreaName(model.getAreaName());
        addressData.setCityCode(model.getCityCode());
        addressData.setCityName(model.getCityName());
        addressData.setProvCode(model.getProvCode());
        addressData.setProvName(model.getProvName());
        return addressData;
    }
}
