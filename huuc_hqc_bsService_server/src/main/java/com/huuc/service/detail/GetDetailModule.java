package com.huuc.service.detail;

/**
 * 各模块获取数据公共接口
 *
 * @param <T>
 * @author hqc
 */
public interface GetDetailModule<T> {
    T getData(DetailParams params);
}
