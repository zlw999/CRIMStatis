package com.crims.apps.dao.state;

import com.crims.apps.model.state.AlarmInfoVO;
import com.crims.apps.model.state.DeviceStatStateInfoVO;
import com.crims.apps.model.state.StationInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface StateStatisticsDao {
    List<AlarmInfoVO> getTodoProcessAlarmInfo();

    List<StationInfoVO> getStationInfo(@Param("lineId") String lineId);

    String findNodeNameById(String lineId);

    List<DeviceStatStateInfoVO> getDeviceStatStateInfoVO(@Param("lineId") String lineId);

    int finddevCount(@Param("lineId") String lineId);

    int findfaultCount(@Param("newStationid") String newStationid);

    List<DeviceStatStateInfoVO> findStation();

    List<AlarmInfoVO> getTodoProcessAlarmInfoByAlarmDisTime();

    List<AlarmInfoVO> getTodoProcessAlarmInfoByAlarmAffirmTime();
}
