package com.training.service;

import com.training.model.Data.StudentData;
import com.training.model.form.StudentForm;
import com.training.page.Pagination;
import com.training.page.SearchResult;

public interface StudentService {

    SearchResult<StudentData> queryAllStudent(StudentForm studentForm, Pagination page);

    void add(StudentForm studentForm);

    StudentData findById(Integer id);

    void updateById(StudentForm studentForm);

    void deleteById(Integer id);

}
