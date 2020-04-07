package com.myth.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myth.equipment.entity.Equipment;
import com.myth.equipment.entity.RoleEqui;
import com.myth.equipment.mapper.RoleEquiMapper;
import com.myth.equipment.service.IRoleEquiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author corningrey
 * @since 2020-04-07
 */
@Service
public class RoleEquiServiceImpl extends ServiceImpl<RoleEquiMapper, RoleEqui> implements IRoleEquiService {
    @Resource
    RoleEquiMapper roleEquiMapper;

    public List<Equipment> selectEquiListByRoleId(Integer roleId) {
        return roleEquiMapper.selectEquiListByRoleId(roleId);
    }
}
