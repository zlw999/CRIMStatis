package com.crims.apps.dao.maintainlog;

import com.crims.apps.model.maintainlog.RecMaintainlogtj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecMaintainlogtjDao {
    void save(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> findLikeMain(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> findAll();

    void deleteAll(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> findMainlogtj(RecMaintainlogtj recMaintainlogtj);

    List<RecMaintainlogtj> finduser();

    List<RecMaintainlogtj> find();

    void delete(RecMaintainlogtj tainlogtj);
}
