package com.crims.apps.dao.nettop;

import com.crims.apps.model.nettop.ConfEnum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfEnumDao {
    String findTypename(ConfEnum confEnum);

    String findMainTypename(ConfEnum confEnum);
}
