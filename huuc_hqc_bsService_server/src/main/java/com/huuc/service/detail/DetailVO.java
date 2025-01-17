package com.huuc.service.detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huuc.service.detail.data.*;
import lombok.Data;

import java.util.List;

@Data
public class DetailVO {
    private UserInfoData userInfo;

    private List<AuditInfoData> auditInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ConfessionInfoData confessionInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LostItemInfoData lostItemInfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClubRecruitmentNewsInfoData clubRecruitmentNewsInfo;
}
