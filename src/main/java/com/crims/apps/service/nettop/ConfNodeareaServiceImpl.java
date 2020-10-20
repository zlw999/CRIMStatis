package com.crims.apps.service.nettop;

import com.crims.apps.dao.nettop.ConfNodeareaDao;
import com.crims.apps.model.nettop.ConfNodearea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfNodeareaServiceImpl implements ConfNodeareaService {
    @Autowired
    private ConfNodeareaDao confNodeareaDao;

    @Override
    public List<ConfNodearea> findAll() {
        return confNodeareaDao.findAll();
    }

    @Override
    public String findNodeNameById(String nodeidnew) {
        return confNodeareaDao.findNodeNameById(nodeidnew);
    }
}