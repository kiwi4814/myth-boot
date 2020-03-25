package com.myth.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thyme
 * @ClassName RedisVo
 * @Description TODO
 * @Date 2019/12/17 20:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisVo implements Serializable {

    private String key;

    private String value;

}
