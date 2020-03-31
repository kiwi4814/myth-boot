package com.myth.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myth.equipment.entity.Equipment;
import com.myth.equipment.entity.ResultEqui;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author corningrey
 * @since 2020-03-27
 */
public interface IEquipmentService extends IService<Equipment> {
    public List<ResultEqui> permutateAll();
}
