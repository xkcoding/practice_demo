package com.xkcoding.strategy.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 人群信息
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-10-11 10:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GroupUser implements Serializable {
    private static final long serialVersionUID = -3124243822960299108L;

    private String username;

    private String email;
}
