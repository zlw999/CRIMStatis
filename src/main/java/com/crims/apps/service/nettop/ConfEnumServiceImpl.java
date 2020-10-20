package com.crims.apps.service.nettop;

import com.crims.apps.dao.nettop.ConfEnumDao;
import com.crims.apps.model.nettop.ConfEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfEnumServiceImpl implements ConfEnumService {
    @Autowired
    private ConfEnumDao confEnumDao;

    @Override
    public String findTypename(ConfEnum confEnum) {
        return confEnumDao.findTypename(confEnum);
    }

    @Override
    public String findMainTypename(ConfEnum confEnum) {
        return confEnumDao.findMainTypename(confEnum);
    }
}
