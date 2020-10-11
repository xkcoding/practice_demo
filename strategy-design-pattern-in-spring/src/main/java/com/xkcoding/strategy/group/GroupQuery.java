package com.xkcoding.strategy.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 人群查询条件
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-10-11 09:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupQuery implements Serializable {
    private static final long serialVersionUID = -2865404826367545020L;

    /**
     * TODO：这里添加各种需要查询的条件
     */
    private String condition;
}
