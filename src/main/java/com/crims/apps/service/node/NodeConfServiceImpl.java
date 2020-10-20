package com.crims.apps.service.node;

import com.crims.apps.dao.confinfo.NodeConfDao;
import com.crims.apps.model.confinfo.ConfNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeConfServiceImpl implements NodeConfService {

    @Autowired
    private NodeConfDao nodeConfDao;

    @Override
    public String getNodeName(String nodeid) {
        return nodeConfDao.getNodeName(nodeid);
    }
}
