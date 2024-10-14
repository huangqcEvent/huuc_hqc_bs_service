package com.huuc.MBAutofill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//公共字段自动填充
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        /*更新操作自动填充*/
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());
        metaObject.setValue("downTime", LocalDateTime.now());
    }
}