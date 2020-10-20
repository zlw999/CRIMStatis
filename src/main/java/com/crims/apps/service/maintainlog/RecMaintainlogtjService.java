package com.crims.apps.service.maintainlog;

import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.maintainlog.RecMaintainlogtj;

import java.util.List;

public interface RecMaintainlogtjService {
    void save(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> findLikeMain(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> findAll();

    void deleteAll(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> findMainlogtj(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> finduser();

    List<RecMaintainlogtj> find();

    void delete(RecMaintainlogtj tainlogtj);
}
