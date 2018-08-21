package com.training.dao;

import com.training.model.Data.StudentData;
import com.training.model.StudentModel;
import com.training.page.Pagination;
import com.training.page.SearchResult;

import java.util.HashMap;
import java.util.Map;

public interface StudentDao {
    SearchResult<StudentModel> queryStudentByFields(Map<String, Object> params, HashMap<String, String> orders, Pagination page);
}
