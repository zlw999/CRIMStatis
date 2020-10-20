package com.crims.apps.service.state;

import com.crims.apps.dao.state.StateStatisticsDao;
import com.crims.apps.model.alarminfo.Alarminfo;
import com.crims.apps.model.state.AlarmInfoVO;
import com.crims.apps.model.state.DeviceStatStateInfoVO;
import com.crims.apps.model.state.FaultlevelStatInfoVO;
import com.crims.apps.model.state.StationInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateStatisticsServiceImpl implements StateStatisticsService {
    @Autowired
    private StateStatisticsDao stateStatisticsDao;

    @Override
    public List<AlarmInfoVO> getTodoProcessAlarmInfo() {
        return stateStatisticsDao.getTodoProcessAlarmInfo();
    }

    @Override
    public List<StationInfoVO> getStationInfo(String lineId) {
        return stateStatisticsDao.getStationInfo(lineId);
    }

    @Override
    public String findNodeNameById(String lineId) {
        return stateStatisticsDao.findNodeNameById(lineId);
    }

    @Override
    public List<DeviceStatStateInfoVO> getDeviceStatStateInfoVO(String lineId) {
        return stateStatisticsDao.getDeviceStatStateInfoVO(lineId);
    }

    @Override
    public int finddevCount(String lineId) {
        return stateStatisticsDao.finddevCount(lineId);
    }

    @Override
    public int findfaultCount(String newStationid) {
        return stateStatisticsDao.findfaultCount(newStationid);
    }

    @Override
    public List<DeviceStatStateInfoVO> findStation() {
        return stateStatisticsDao.findStation();
    }

    @Override
    public List<AlarmInfoVO> getTodoProcessAlarmInfoByAlarmDisTime() {
        return stateStatisticsDao.getTodoProcessAlarmInfoByAlarmDisTime();
    }

    @Override
    public List<AlarmInfoVO> getTodoProcessAlarmInfoByAlarmAffirmTime() {
        return stateStatisticsDao.getTodoProcessAlarmInfoByAlarmAffirmTime();
    }

    @Override
    public List<FaultlevelStatInfoVO> findStationforFault() {
        return stateStatisticsDao.findStationforFault();
    }

    @Override
    public List<Alarminfo> tjAlarminfo(String id) {
        return stateStatisticsDao.tjAlarminfo(id);
    }
}
