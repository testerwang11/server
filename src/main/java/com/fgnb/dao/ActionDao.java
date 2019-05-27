package com.fgnb.dao;

import com.fgnb.mbg.po.Action;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiangyitao.
 */
public interface ActionDao {
    List<Action> selectByStepActionId(@Param("stepActionId") Integer stepActionId);
}
