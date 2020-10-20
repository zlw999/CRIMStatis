package com.crims.apps.service.alarminfo;

import com.crims.apps.dao.alarminfo.AlarminfoDao;
import com.crims.apps.model.alarminfo.Alarminfo;
import com.crims.apps.model.alarminfo.TjAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarminfoServiceImpl implements AlarminfoService {
    @Autowired
    private AlarminfoDao alarminfoDao;


    @Override
    public List<Alarminfo> findAlarm(TjAlarm tjAlarm) {
        return alarminfoDao.findAlarm(tjAlarm);
    }

    @Override
    public List<Alarminfo> findAlarmCondition(TjAlarm tjAlarm) {
        return alarminfoDao.findAlarmCondition(tjAlarm);
    }
}
