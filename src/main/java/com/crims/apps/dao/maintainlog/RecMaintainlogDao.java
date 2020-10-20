package com.crims.apps.dao.maintainlog;

import com.crims.apps.model.maintainlog.RecMaintainlog;
import com.crims.apps.model.maintainlog.RecMaintainlogtj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RecMaintainlogDao {
    List<RecMaintainlog> findAllLog();

    List<RecMaintainlog> findMaintaintime(String key);

    RecMaintainlog findMaintaintime1(String key);

    String findDevicename(String key);

    List<RecMaintainlog> findMainlog(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlog> findMainlogCondition(RecMaintainlogtj recMaintainlogtj);
}
