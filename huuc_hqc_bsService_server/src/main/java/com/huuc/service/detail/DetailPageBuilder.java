package com.huuc.service.detail;
import com.huuc.service.detail.module.AuditInfoDataModule;
import com.huuc.service.detail.module.PostInfoModule;
import com.huuc.service.detail.module.UserInfoModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 建造者类 生成目标数据集合
 */
public class DetailPageBuilder {
    private final List<GetDetailModule<?>> commonModules;
    private final List<GetDetailModule<?>> specificModules;

    public DetailPageBuilder(AuditInfoDataModule auditInfoDataModule, PostInfoModule postInfoModule, UserInfoModule userInfoModule) {
        this.commonModules = Arrays.asList(auditInfoDataModule, postInfoModule,userInfoModule);
        this.specificModules = new ArrayList<>();
    }

    public DetailPageBuilder addSpecificModule(GetDetailModule<?> module) {
        this.specificModules.add(module);
        return this;
    }

    public DetailPage build() {
        List<GetDetailModule<?>> allModules = new ArrayList<>(commonModules);
        allModules.addAll(specificModules);
        return new DetailPage(allModules);
    }
}
