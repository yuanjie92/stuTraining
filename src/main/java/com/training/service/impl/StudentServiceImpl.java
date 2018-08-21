package com.training.service.impl;

import com.training.common.service.CommonService;
import com.training.convert.Convert;
import com.training.convert.impl.AddressFormConvert2Model;
import com.training.dao.StudentDao;
import com.training.model.AddressModel;
import com.training.model.Data.AddressData;
import com.training.model.Data.StudentData;
import com.training.model.StudentModel;
import com.training.model.form.StudentForm;
import com.training.page.Pagination;
import com.training.page.SearchResult;
import com.training.service.StudentService;

import com.training.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private CommonService commonService;
    private Convert<StudentModel, StudentData> studentConvert;
    private Convert<AddressModel,AddressData> addressConvert;
    private Convert<StudentForm, StudentModel> convertFrom2model;
    private AddressFormConvert2Model addressFormConvert2Model;


    @Override
    public SearchResult<StudentData> queryAllStudent(StudentForm studentForm, Pagination page) {
        Map<String, Object> params = new HashMap<>();
        HashMap<String, String> orders = new HashMap<>();
        if(StringUtils.isNotBlank(studentForm.getName())){
            params.put(StudentModel.NAME,studentForm.getName());
        }
        if(StringUtils.isNotBlank(studentForm.getClazz())){
            params.put(StudentModel.CLAZZ, studentForm.getClazz());
        }
        orders.put("desc","createDate");
        SearchResult<StudentModel> searchResult = studentDao.queryStudentByFields(params, orders, page);
        SearchResult<StudentData> results = new SearchResult<>();
        List<StudentData> datas = new ArrayList<>();
        for(StudentModel model : searchResult.getResults()){

            StudentData studentData = studentConvert.convert(model);
            datas.add(studentData);

            AddressModel addressModel = model.getAddress();
            AddressData addressData = addressConvert.convert(addressModel);

            studentData.setAddressData(addressData);

        }

        results.setPagination(page);
        results.setResults(datas);
        return results;
    }


    @Override
    public void add(StudentForm studentForm) {
        StudentModel studentModel = convertFrom2model.convert(studentForm);

        AddressModel addressmodel = addressFormConvert2Model.convert(studentForm.getAddressForm());

        commonService.saveOrUpdateEntity(addressmodel);

        studentModel.setAddress(addressmodel);

        commonService.saveOrUpdateEntity(studentModel);
    }

    @Override
    public StudentData findById(Integer id) {
        StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, id);
        StudentData studentData = studentConvert.convert(studentModel);
        AddressData address  = addressConvert.convert(studentModel.getAddress());
        studentData.setAddressData(address);
        return studentData;
    }

    @Override
    public void updateById(StudentForm studentForm) {
        StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, studentForm.getId());
        studentModel.setBirthday(DateUtil.getDateByString(studentForm.getBirthday()));
        studentModel.setAge(studentForm.getAge());
        studentModel.setClazz(studentForm.getClazz());
        studentModel.setModifyDate(new Date());
        studentModel.setName(studentForm.getName());
        commonService.saveOrUpdateEntity(studentModel);
    }

    @Override
    public void deleteById(Integer id) {
        StudentModel studentModel = (StudentModel) commonService.load(StudentModel.class, id);
        commonService.delete(studentModel);
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public Convert<StudentModel, StudentData> getStudentConvert() {
        return studentConvert;
    }

    public void setStudentConvert(Convert<StudentModel, StudentData> studentConvert) {
        this.studentConvert = studentConvert;
    }

    public Convert<StudentForm, StudentModel> getConvertFrom2model() {
        return convertFrom2model;
    }

    public void setConvertFrom2model(Convert<StudentForm, StudentModel> convertFrom2model) {
        this.convertFrom2model = convertFrom2model;
    }

    public Convert<AddressModel, AddressData> getAddressConvert() {
        return addressConvert;
    }

    public void setAddressConvert(Convert<AddressModel, AddressData> addressConvert) {
        this.addressConvert = addressConvert;
    }

    public AddressFormConvert2Model getAddressFormConvert2Model() {
        return addressFormConvert2Model;
    }

    public void setAddressFormConvert2Model(AddressFormConvert2Model addressFormConvert2Model) {
        this.addressFormConvert2Model = addressFormConvert2Model;
    }
}
