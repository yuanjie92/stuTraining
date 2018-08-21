package com.training.dao.impl;

import com.training.common.dao.CommonDao;
import com.training.dao.StudentDao;
import com.training.model.StudentModel;
import com.training.page.Pagination;
import com.training.page.SearchResult;

import java.util.HashMap;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {

    private CommonDao commonDao;

    @Override
    public SearchResult<StudentModel> queryStudentByFields(Map<String, Object> params, HashMap<String, String> orders, Pagination pagination) {
        return commonDao.getEntitiesByFields(StudentModel.class, params, orders, pagination);
    }

    public CommonDao getCommonDao() {
        return commonDao;
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }
}
