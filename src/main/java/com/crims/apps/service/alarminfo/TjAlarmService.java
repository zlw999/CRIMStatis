package com.crims.apps.service.alarminfo;

import com.crims.apps.model.alarminfo.TjAlarm;

import java.util.List;

public interface TjAlarmService {
    List<TjAlarm> findtjAlarm(TjAlarm tjAlarm);

    void deleteAll(TjAlarm tainlogtj);

    List<TjAlarm> findAll();

    void save(TjAlarm rectj);

    List<TjAlarm> tjlie(String s);

    void delete(TjAlarm tjAlarm);
}
