package com.xkcoding.quartz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 测试 Spring Bean
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-06-05 11:06
 */
@Slf4j
@Service
public class DemoService {
    public void echo(String module) {
        System.out.println("[DemoService] echo in [" + module + "] at " + LocalDateTime.now() + "");
    }
}
