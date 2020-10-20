package com.crims.apps.service.maintainlog;

import com.crims.apps.dao.maintainlog.RecMaintainlogDao;
import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.maintainlog.RecMaintainlogtj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecMaintainlogServiceImpl implements RecMaintainlogService {
    @Autowired
    private RecMaintainlogDao recMaintainlogDao;
    @Override
    public List<RecMaintainlog> findAllLog() {
        return recMaintainlogDao.findAllLog();
    }

    @Override
    public List<RecMaintainlog> findMaintaintime(String key) {
        return recMaintainlogDao.findMaintaintime(key);
    }

    @Override
    public RecMaintainlog findMaintaintime1(String key) {
        return recMaintainlogDao.findMaintaintime1(key);
    }

    @Override
    public String findDevicename(String key) {
        return recMaintainlogDao.findDevicename(key);
    }

    @Override
    public List<RecMaintainlog> findMainlog(RecMaintainlogtj recMaintainlogtj) {
        return recMaintainlogDao.findMainlog(recMaintainlogtj);
    }

    @Override
    public List<RecMaintainlog> findMainlogCondition(RecMaintainlogtj recMaintainlogtj) {
        return recMaintainlogDao.findMainlogCondition(recMaintainlogtj);
    }
}
