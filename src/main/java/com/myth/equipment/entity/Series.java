package com.myth.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author corningrey
 * @since 2020-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Series implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String code;

    private Integer count;

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


}
