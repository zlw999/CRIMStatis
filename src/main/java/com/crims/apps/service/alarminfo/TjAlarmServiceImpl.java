package com.crims.apps.service.alarminfo;

import com.crims.apps.dao.alarminfo.TjAlarmDao;
import com.crims.apps.model.alarminfo.TjAlarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TjAlarmServiceImpl implements TjAlarmService {
    @Autowired
    private TjAlarmDao tjAlarmDao;
    @Override
    public List<TjAlarm> findtjAlarm(TjAlarm tjAlarm) {
        return tjAlarmDao.findtjAlarm(tjAlarm);
    }

    @Override
    public void deleteAll(TjAlarm tainlogtj) {
        tjAlarmDao.deleteAll(tainlogtj);
    }

    @Override
    public List<TjAlarm> findAll() {
        return tjAlarmDao.findAll();
    }

    @Override
    public void save(TjAlarm rectj) {
        tjAlarmDao.save(rectj);
    }

    @Override
    public List<TjAlarm> tjlie(String s) {
        return tjAlarmDao.tjlie(s);
    }

    @Override
    public void delete(TjAlarm tjAlarm) {
        tjAlarmDao.delete(tjAlarm);
    }
}
