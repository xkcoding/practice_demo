package com.xkcoding.strategy.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 人群选择工厂类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-10-11 10:00
 */
@Service
public class GroupSelectFactory {
    @Autowired
    private List<IGroupSelect> groupSelectList;

    /**
     * 根据人群类型选择具体的实现类
     *
     * @param type 人群类型
     * @return 人群选择具体实现类
     */
    public IGroupSelect getGroupSelect(GroupType type) {
        Optional<IGroupSelect> groupSelectOptional = groupSelectList.stream().filter(t -> t.type() == type).findAny();
        return groupSelectOptional.orElseThrow(() -> new IllegalArgumentException("暂不支持该人群方式"));
    }
}
