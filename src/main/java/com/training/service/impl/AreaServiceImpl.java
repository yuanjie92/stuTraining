package com.training.service.impl;

import com.training.common.dao.CommonDao;
import com.training.model.AreaModel;
import com.training.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class AreaServiceImpl implements AreaService {

    @Autowired
    private CommonDao commonDao;

    @Override
    public void insert(AreaModel area) {
        commonDao.saveOrUpdateEntity(area);
    }

    @Override
    public List<AreaModel> getByPid(Class clazz, String pCode) {
        return commonDao.getEntitiesByField(clazz,"pCode",pCode,null);
    }

    @Override
    public void saveAll(Collection<AreaModel> all) {
        //commonDao.saveOrUpdateAllEntity(all);
        commonDao.saveOrUpdateAllEntity2(all);
    }

    public CommonDao getCommonDao() {
        return commonDao;
    }

    public void setCommonDao(CommonDao commonDao) {
        this.commonDao = commonDao;
    }
}
