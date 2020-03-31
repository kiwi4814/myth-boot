package com.myth.equipment.entity;

import lombok.Data;

import java.util.List;

@Data
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
}
