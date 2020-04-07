package com.myth.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author corningrey
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer shuxing;

    private Integer shuqiang;

    private Double sangongRate;

    private Double baiziRate;

    private Double huangziRate;

    private Double zhongshangRate;

    private Double baoshangRate;

    private Double lizhiRate;

    private Double skillRate;



    public void addEquipment(Equipment e) {
        Integer SQ = e.getShuqiang() == null ? 0 : e.getShuqiang();
        Double SG = e.getSangongRate() == null ? 0 : e.getSangongRate();
        Double BZ = e.getBaiziRate() == null ? 0 : e.getBaiziRate();
        Double HZ = e.getHuangziRate() == null ? 0 : e.getHuangziRate();
        Double BS = e.getBaoshangRate() == null ? 0 : e.getBaoshangRate();
        Double ZS = e.getZhongshangRate() == null ? 0 : e.getZhongshangRate();
        double SK = e.getSkillRate() == null ? 0 : e.getSkillRate();
        Double LZ = e.getLizhiRate() == null ? 0 : e.getLizhiRate();
        this.shuqiang = this.shuqiang == null ? 0 : this.shuqiang + SQ;
        this.sangongRate = this.sangongRate == null ? 0 : this.sangongRate + SG;
        this.baiziRate = this.baiziRate == null ? 0 : this.baiziRate + BZ;
        this.huangziRate = this.huangziRate == null ? 0 : this.huangziRate + HZ;
        this.baoshangRate = this.baoshangRate == null ? 0 : this.baoshangRate + BS;
        this.zhongshangRate = this.zhongshangRate == null ? 0 : this.zhongshangRate + ZS;
        this.skillRate = this.skillRate == null ? 0 : (1 + this.skillRate) * (1 + SK) - 1;
        this.lizhiRate = this.lizhiRate == null ? 0 : this.lizhiRate + LZ;
    }
}
