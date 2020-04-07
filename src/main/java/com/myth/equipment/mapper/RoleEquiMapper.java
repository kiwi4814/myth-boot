package com.myth.equipment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myth.equipment.entity.Equipment;
import com.myth.equipment.entity.RoleEqui;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author corningrey
 * @since 2020-04-07
 */
public interface RoleEquiMapper extends BaseMapper<RoleEqui> {

    List<Equipment> selectEquiListByRoleId(Integer roleId);
}
