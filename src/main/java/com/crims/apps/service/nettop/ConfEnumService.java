package com.crims.apps.service.nettop;

import com.crims.apps.model.nettop.ConfEnum;

public interface ConfEnumService {
    String findTypename(ConfEnum confEnum);

    String findMainTypename(ConfEnum confEnum);
}
