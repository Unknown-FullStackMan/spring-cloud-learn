package com.simple.myhystrix.model;

/**
 * 熔断开关状态
 */
public enum HystrixStatus {
    CLOSE,
    OPEN,
    HALF_OPEN
}
