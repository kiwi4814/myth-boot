package com.myth.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author corningrey
 * @since 2020-03-27
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String series;

    private Double liliang;

    private Double zhili;

    private Double duli;

    private Double gongji;

    private Integer shuqiang;

    private Double sangongRate;

    private Double baiziRate;

    private Double huangziRate;

    private Double zhongshangRate;

    private Double baoshangRate;

    private Double lizhiRate;

    private Double skillRate;

    private String location;

    private String shenhua;
}
