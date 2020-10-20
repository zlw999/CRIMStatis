package com.crims.apps.service.alarminfo;

import com.crims.apps.dao.alarminfo.TJAlarmVqdDao;
import com.crims.apps.model.alarminfo.TJAlarmVqd;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TJAlarmVqdServiceImpl implements TJAlarmVqdService{

    @Autowired
    private TJAlarmVqdDao tjAlarmVqdDao;

    @Override
    public List<TJAlarmVqd> getAll() {
        return tjAlarmVqdDao.getAll();
    }

    @Override
    public PageInfo<TJAlarmVqd> getTJAlarmByAreaID(int currentPage, int pageSize, String areaid, Date vqddate) {
        PageHelper.startPage(currentPage, pageSize);
        List<TJAlarmVqd> pageList = tjAlarmVqdDao.getTJAlarmByAreaID(areaid,vqddate);
        PageInfo<TJAlarmVqd> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<TJAlarmVqd> getTJAlarmByAIDAndLID(int currentPage, int pageSize, String areaid, String lineid, Date vqddate) {
        PageHelper.startPage(currentPage, pageSize);
        List<TJAlarmVqd> pageList = tjAlarmVqdDao.getTJAlarmByAIDAndLID(areaid,lineid,vqddate);
        PageInfo<TJAlarmVqd> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<TJAlarmVqd> getTJAlarmByAIDAndLIDAndSID(int currentPage, int pageSize, String areaid, String lineid, String stationid, Date vqddate) {
        PageHelper.startPage(currentPage, pageSize);
        List<TJAlarmVqd> pageList = tjAlarmVqdDao.getTJAlarmByAIDAndLIDAndSID(areaid,lineid,stationid,vqddate);
        PageInfo<TJAlarmVqd> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<TJAlarmVqd> getTJAlarmByAll(int currentPage, int pageSize, String areaid, String lineid, String stationid, String alarmtype, Date vqddate) {
        PageHelper.startPage(currentPage, pageSize);
        List<TJAlarmVqd> pageList = tjAlarmVqdDao.getTJAlarmByAll(areaid,lineid,stationid,alarmtype,vqddate);
        PageInfo<TJAlarmVqd> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<TJAlarmVqd> getTJAlarmByAIDAndAType(int currentPage, int pageSize, String areaid, String alarmtype, Date vqddate) {
        PageHelper.startPage(currentPage, pageSize);
        List<TJAlarmVqd> pageList = tjAlarmVqdDao.getTJAlarmByAIDAndAType(areaid,alarmtype,vqddate);
        PageInfo<TJAlarmVqd> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<TJAlarmVqd> getTJAlarmByAIDAndLIDAndAType(int currentPage, int pageSize, String areaid, String lineid, String alarmtype, Date vqddate) {
        PageHelper.startPage(currentPage, pageSize);
        List<TJAlarmVqd> pageList = tjAlarmVqdDao.getTJAlarmByAIDAndLIDAndAType(areaid,lineid,alarmtype,vqddate);
        PageInfo<TJAlarmVqd> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<TJAlarmVqd> getTJAlarmByAType(int currentPage, int pageSize, String alarmtype, Date vqddate) {
        PageHelper.startPage(currentPage, pageSize);
        List<TJAlarmVqd> pageList = tjAlarmVqdDao.getTJAlarmByAType(alarmtype,vqddate);
        PageInfo<TJAlarmVqd> pageInfo = new PageInfo<>(pageList);
        return pageInfo;
    }
}
