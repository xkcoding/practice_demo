package com.xkcoding.strategy.group;

/**
 * <p>
 * 人群选择方式
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-10-11 09:41
 */
public enum GroupType {
    /**
     * 从 ES 查询
     */
    ES,
    /**
     * 从 MongoDB 查询
     */
    MONGODB,
    /**
     * 从 文件 读取
     */
    FILE
}
