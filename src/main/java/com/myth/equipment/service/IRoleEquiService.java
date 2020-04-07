package com.myth.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myth.equipment.entity.Equipment;
import com.myth.equipment.entity.RoleEqui;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author corningrey
 * @since 2020-04-07
 */
public interface IRoleEquiService extends IService<RoleEqui> {
    List<Equipment> selectEquiListByRoleId(Integer roleId);
}
