package com.training.convert.impl;

import com.training.common.config.Config;
import com.training.convert.Convert;
import com.training.model.Data.AddressData;
import com.training.model.Data.StudentData;
import com.training.model.StudentModel;

import java.io.File;

public class StudentConvert implements Convert<StudentModel, StudentData> {

	@Override
	public StudentData createTarget() {
		return new StudentData();
	}

	@Override
	public StudentData convert(StudentModel model) {
		StudentData data = createTarget();
		data.setId(model.getId());
		data.setName(model.getName());
		data.setAge(model.getAge());
		data.setBirthday(model.getBirthday());
		data.setClazz(model.getClazz());
		data.setCreateDate(model.getCreateDate());
		data.setModifyDate(model.getModifyDate());
		data.setHeadImg( model.getHeadImg());
		return data;
	}

}
