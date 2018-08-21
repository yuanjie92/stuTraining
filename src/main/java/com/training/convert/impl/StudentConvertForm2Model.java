package com.training.convert.impl;

import java.util.Date;

import com.training.convert.Convert;
import com.training.model.AddressModel;
import com.training.model.StudentModel;
import com.training.model.form.StudentForm;
import com.training.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentConvertForm2Model implements Convert<StudentForm, StudentModel> {

	@Override
	public StudentModel createTarget() {
		return new StudentModel();
	}



	@Override
	public StudentModel convert(StudentForm studentForm) {
		StudentModel studentModel = createTarget();
		studentModel.setBirthday(DateUtil.getDateByString(studentForm.getBirthday()));
		studentModel.setClazz(studentForm.getClazz());
		studentModel.setCreateDate(new Date());
		studentModel.setId(studentForm.getId());
		studentModel.setModifyDate(new Date());
		studentModel.setName(studentForm.getName());
		studentModel.setAge(studentForm.getAge());
		studentModel.setAreaCode(studentForm.getAreaCode());
		studentModel.setHeadImg(studentForm.getHeadImg());

		return studentModel;
	}

}
