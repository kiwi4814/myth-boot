package com.myth.system.service.impl;

import com.myth.system.dao.SysUserRoleDao;
import com.myth.system.entity.SysUserRole;
import com.myth.system.service.SysUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cuiyating
 * @date 2020/1/12 0:45
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserRoleServiceImpl implements SysUserRoleService {

    private final SysUserRoleDao sysUserRoleDao;

    @Override
    public int insert(SysUserRole sysUserRole) {
        return sysUserRoleDao.insert(sysUserRole);
    }

    @Override
    public int deleteByUserId(String userId) {
        return sysUserRoleDao.deleteByUserId(userId);
    }
}
