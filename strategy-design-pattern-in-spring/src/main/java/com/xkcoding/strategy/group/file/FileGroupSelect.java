package com.xkcoding.strategy.group.file;

import com.xkcoding.strategy.group.GroupQuery;
import com.xkcoding.strategy.group.GroupType;
import com.xkcoding.strategy.group.GroupUser;
import com.xkcoding.strategy.group.IGroupSelect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文件 人群选择
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-10-11 10:12
 */
@Slf4j
@Service
public class FileGroupSelect implements IGroupSelect {
    /**
     * 人群获取方式
     *
     * @return 人群选择枚举
     */
    @Override
    public GroupType type() {
        return GroupType.FILE;
    }

    /**
     * 事前校验查询条件
     *
     * @param groupQuery 查询条件
     * @throws IllegalArgumentException 参数异常
     */
    @Override
    public void checkQueryCondition(GroupQuery groupQuery) throws IllegalArgumentException {
        log.info("groupQuery = {}", groupQuery);
    }

    /**
     * 查询满足条件的用户
     *
     * @param groupQuery 查询条件
     * @return 满足条件的用户
     */
    @Override
    public List<GroupUser> doQuery(GroupQuery groupQuery) {
        List<GroupUser> result = new ArrayList<>();
        // TODO:
        //  1. 复杂的解析、读文件
        //  2. 根据条件筛选满足条件的用户数据
        for (int i = 1; i <= 3; i++) {
            result.add(GroupUser.of("文件读取用户" + i, i + "@file.com"));
        }
        return result;
    }
}
