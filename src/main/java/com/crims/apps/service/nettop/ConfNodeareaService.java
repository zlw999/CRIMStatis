package com.crims.apps.service.nettop;

import com.crims.apps.model.nettop.ConfNodearea;

import java.util.List;

public interface ConfNodeareaService {
    List<ConfNodearea> findAll();

    String findNodeNameById(String nodeidnew);
}
