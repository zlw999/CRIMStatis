package com.crims.apps.service.state;

import com.crims.apps.model.alarminfo.Alarminfo;
import com.crims.apps.model.state.AlarmInfoVO;
import com.crims.apps.model.state.DeviceStatStateInfoVO;
import com.crims.apps.model.state.FaultlevelStatInfoVO;
import com.crims.apps.model.state.StationInfoVO;

import java.util.List;

public interface StateStatisticsService {
    List<AlarmInfoVO> getTodoProcessAlarmInfo();

    List<StationInfoVO> getStationInfo(String lineId);

    String findNodeNameById(String lineId);

    List<DeviceStatStateInfoVO> getDeviceStatStateInfoVO(String lineId);

    int finddevCount(String lineId);

    int findfaultCount(String newStationid);

    List<DeviceStatStateInfoVO> findStation();

    List<AlarmInfoVO> getTodoProcessAlarmInfoByAlarmDisTime();

    List<AlarmInfoVO> getTodoProcessAlarmInfoByAlarmAffirmTime();

    List<FaultlevelStatInfoVO> findStationforFault();

    List<Alarminfo> tjAlarminfo(String id);
}
