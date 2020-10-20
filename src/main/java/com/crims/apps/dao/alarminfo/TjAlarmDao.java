package com.crims.apps.dao.alarminfo;

import com.crims.apps.model.alarminfo.TjAlarm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TjAlarmDao {
    List<TjAlarm> findtjAlarm(TjAlarm tjAlarm);

    void deleteAll(TjAlarm tainlogtj);

    List<TjAlarm> findAll();

    void save(TjAlarm rectj);

    List<TjAlarm> tjlie(@Param("String") String s);

    void delete(TjAlarm tjAlarm);
}
