package com.xkcoding.strategy.group;

import java.util.List;

/**
 * <p>
 * 人群选择接口
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-10-11 09:41
 */
public interface IGroupSelect {
    /**
     * 人群获取方式
     *
     * @return 人群选择枚举
     */
    GroupType type();

    /**
     * 查询满足条件的用户
     *
     * @param groupQuery 查询条件
     * @return 满足条件的用户
     */
    default List<GroupUser> queryUser(GroupQuery groupQuery) {
        checkQueryCondition(groupQuery);
        return doQuery(groupQuery);
    }

    /**
     * 事前校验查询条件
     *
     * @param groupQuery 查询条件
     * @throws IllegalArgumentException 参数异常
     */
    void checkQueryCondition(GroupQuery groupQuery) throws IllegalArgumentException;

    /**
     * 真正的查询方法
     *
     * @param groupQuery 查询条件
     * @return 满足条件的用户
     */
    List<GroupUser> doQuery(GroupQuery groupQuery);

}
