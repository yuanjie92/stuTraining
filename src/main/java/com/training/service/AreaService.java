package com.training.service;

import com.training.model.AreaModel;

import java.util.Collection;
import java.util.List;

public interface AreaService {

    void insert(AreaModel area);

    List<AreaModel> getByPid(Class<AreaModel> clazz, String pCode);

    void saveAll(Collection<AreaModel> all);
}
