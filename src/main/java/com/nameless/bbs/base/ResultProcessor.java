package com.nameless.bbs.base;


import com.nameless.bbs.dto.RespResult;

@FunctionalInterface
public interface ResultProcessor {
    RespResult process();
}
