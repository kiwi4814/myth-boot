package com.myth.equipment.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResultEqui {
    private List<Equipment> equiList;
    private Double score;

    private Integer shuqiang;

    private Double sangongRate;

    private Double baiziRate;

    private Double huangziRate;

    private Double zhongshangRate;

    private Double baoshangRate;

    private Double lizhiRate;

    private Double skillRate;

    public ResultEqui(Roles roles, Equipment e) {
        Integer SQ = e.getShuqiang() == null ? 0 : e.getShuqiang();
        Double SG = e.getSangongRate() == null ? 0 : e.getSangongRate();
        Double BZ = e.getBaiziRate() == null ? 0 : e.getBaiziRate();
        Double HZ = e.getHuangziRate() == null ? 0 : e.getHuangziRate();
        Double BS = e.getBaoshangRate() == null ? 0 : e.getBaoshangRate();
        Double ZS = e.getZhongshangRate() == null ? 0 : e.getZhongshangRate();
        double SK = e.getSkillRate() == null ? 0 : e.getSkillRate();
        Double LZ = e.getLizhiRate() == null ? 0 : e.getLizhiRate();
        this.shuqiang = roles.getShuqiang() == null ? 0 : roles.getShuqiang() + SQ;
        this.sangongRate = roles.getSangongRate() == null ? 0 : roles.getSangongRate() + SG;
        this.baiziRate = roles.getBaiziRate() == null ? 0 : roles.getBaiziRate() + BZ;
        this.huangziRate = roles.getHuangziRate() == null ? 0 : roles.getHuangziRate() + HZ;
        this.baoshangRate = roles.getBaoshangRate() == null ? 0 : roles.getBaoshangRate() + BS;
        this.zhongshangRate = roles.getZhongshangRate() == null ? 0 : roles.getZhongshangRate() + ZS;
        this.skillRate = roles.getSkillRate() == null ? 0 : (1 + roles.getSkillRate()) * (1 + SK) - 1;
        this.lizhiRate = roles.getLizhiRate() == null ? 0 : roles.getLizhiRate() + LZ;
        double shuqiangxishu = 1.05 + 0.0045 * this.shuqiang;
        double lizhixishu = ((1 + lizhiRate) * roles.getShuxing()) / 250 + 1;
        this.score = lizhixishu * shuqiangxishu
                * (1 + this.baiziRate)
                * (1 + this.huangziRate)
                * (1 + this.baoshangRate)
                * (1 + this.zhongshangRate)
                * (1 + this.sangongRate)
                * (1 + this.skillRate);
    }
}
