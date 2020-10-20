package com.crims.apps.service.ddman;

public interface DDManService {

    /**
     * 根据ID和ITEM_VALUE获取ITEM_NAME
     * @param id
     * @param itemValue
     * @return String
     */
    String getItemName(String id,String itemValue);

}
