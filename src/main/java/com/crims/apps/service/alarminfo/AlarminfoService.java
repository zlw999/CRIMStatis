package com.crims.apps.service.alarminfo;

import com.crims.apps.model.alarminfo.Alarminfo;
import com.crims.apps.model.alarminfo.TjAlarm;

import java.util.List;

public interface AlarminfoService {

    List<Alarminfo> findAlarm(TjAlarm tjAlarm);

    List<Alarminfo> findAlarmCondition(TjAlarm tjAlarm);
}
