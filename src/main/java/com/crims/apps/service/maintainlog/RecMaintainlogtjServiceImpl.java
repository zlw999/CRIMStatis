package com.crims.apps.service.maintainlog;

import com.crims.apps.dao.maintainlog.RecMaintainlogtjDao;
import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.maintainlog.RecMaintainlogtj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecMaintainlogtjServiceImpl implements RecMaintainlogtjService {
    @Autowired
    private RecMaintainlogtjDao recMaintainlogtjDao;

    @Override
    public void save(RecMaintainlogtj recMaintainlogtj) {
        recMaintainlogtjDao.save(recMaintainlogtj);

    }

    @Override
    public List<RecMaintainlogtj> findLikeMain(RecMaintainlogtj recMaintainlogtj) {
        return recMaintainlogtjDao.findLikeMain(recMaintainlogtj);
    }

    @Override
    public List<RecMaintainlogtj> findAll() {
        return recMaintainlogtjDao.findAll();
    }

    @Override
    public void deleteAll(RecMaintainlogtj RecMaintainlogtj) {
        recMaintainlogtjDao.deleteAll(RecMaintainlogtj);
    }

    @Override
    public List<RecMaintainlogtj> findMainlogtj(RecMaintainlogtj recMaintainlogtj) {
        return recMaintainlogtjDao.findMainlogtj(recMaintainlogtj);
    }

    @Override
    public List<RecMaintainlogtj> finduser() {
        return recMaintainlogtjDao.finduser();
    }

    @Override
    public List<RecMaintainlogtj> find() {
        return recMaintainlogtjDao.find();
    }

    @Override
    public void delete(RecMaintainlogtj tainlogtj) {
        recMaintainlogtjDao.delete(tainlogtj);
    }
}
