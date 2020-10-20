package com.crims.apps.service.ddman;

import com.crims.apps.dao.confinfo.DDManDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DDManServiceImpl implements DDManService {

    @Autowired
    private DDManDao ddManDao;

    @Override
    public String getItemName(String id, String itemValue) {
        return ddManDao.getItemName(id,itemValue);
    }
}
