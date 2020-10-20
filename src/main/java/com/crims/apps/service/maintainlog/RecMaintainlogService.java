package com.crims.apps.service.maintainlog;

import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.maintainlog.RecMaintainlogtj;

import java.util.List;

public interface RecMaintainlogService {
    List<RecMaintainlog> findAllLog();

    List<RecMaintainlog> findMaintaintime(String key);

    RecMaintainlog findMaintaintime1(String key);

    String findDevicename(String key);

    List<RecMaintainlog> findMainlog(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlog> findMainlogCondition(RecMaintainlogtj recMaintainlogtj);
}
