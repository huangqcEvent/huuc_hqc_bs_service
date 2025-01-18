package com.huuc.service.detail;

import com.huuc.enums.PostTypeEnum;
import com.huuc.exception.ResponseException;
import com.huuc.service.detail.data.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 详情页构建器
 *
 * @author hqc
 */
@Data
@Component
@Slf4j
public class DetailPage {

    private final List<GetDetailModule<?>> modules;

    public DetailPage(List<GetDetailModule<?>> modules) {
        this.modules = modules;
    }

    //异步查询所有模块的数据
    public DetailVO getAllData(DetailParams params) {
        DetailVO detailVo = new DetailVO();

        List<CompletableFuture<Void>> futures = modules.stream()
                .map(module -> CompletableFuture.runAsync(() -> {
                    String moduleName = ClassUtils.getShortName(module.getClass());
                    switch (moduleName) {
                        case "UserInfoModule":
                            detailVo.setUserInfo((UserInfoData) module.getData(params));
                            break;
                        case "AuditInfoDataModule":
                            detailVo.setAuditInfo((List<AuditInfoData>) module.getData(params));
                            break;
                        case "PostInfoModule":
                            if (Objects.equals(params.getPostType(), PostTypeEnum.POST_TYPE_CONFESSION.getCode())){
                                detailVo.setConfessionInfo((ConfessionInfoData) module.getData(params));
                            } else if (Objects.equals(params.getPostType(), PostTypeEnum.POST_TYPE_ITEM.getCode())){
                                detailVo.setLostItemInfo((LostItemInfoData) module.getData(params));
                            } else if (Objects.equals(params.getPostType(), PostTypeEnum.POST_TYPE_CLUB.getCode())){
                                detailVo.setClubRecruitmentNewsInfo((ClubRecruitmentNewsInfoData) module.getData(params));
                            }
                            break;
                        default:
                            log.error("类名匹配失败{}", module.getClass().getSimpleName());
                            throw new ResponseException("类名匹配失败" + module.getClass().getSimpleName());
                    }
                })).collect(Collectors.toList());

        try {
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        } catch (Exception e) {
            log.error("详情页异步查询任务执行失败", e);
            throw new ResponseException("无法查询对应详情！");
        }
        return detailVo;
    }
}
