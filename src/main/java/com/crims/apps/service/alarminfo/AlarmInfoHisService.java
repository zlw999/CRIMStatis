package com.crims.apps.service.alarminfo;

import com.crims.apps.model.alarminfo.AlarmInfoHis;

import java.util.Date;
import java.util.List;

public interface AlarmInfoHisService {

    List<AlarmInfoHis> getAlarmInfoHisList(Date startDate,Date endDate);

}
