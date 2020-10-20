package com.crims.apps.dao.confinfo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DDManDao {

    /**
     * 根据ID和ITEM_VALUE获取ITEM_NAME
     * @param id
     * @param itemValue
     * @return String
     */
    String getItemName(String id,String itemValue);

}
