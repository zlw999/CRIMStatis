package com.crims.apps.dao.confinfo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NodeConfDao {

    String getNodeName(String nodeid);

}
