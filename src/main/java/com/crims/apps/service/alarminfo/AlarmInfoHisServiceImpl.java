package com.crims.apps.service.alarminfo;

import com.crims.apps.dao.alarminfo.AlarmInfoHisDao;
import com.crims.apps.model.alarminfo.AlarmInfoHis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlarmInfoHisServiceImpl implements AlarmInfoHisService{

    @Autowired
    private AlarmInfoHisDao alarmInfoHisDao;

    @Override
    public List<AlarmInfoHis> getAlarmInfoHisList(Date startDate, Date endDate) {
        return alarmInfoHisDao.getAlarmInfoHisList(startDate,endDate);
    }
}
