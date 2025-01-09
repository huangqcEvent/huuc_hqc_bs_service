package com.huuc.SensitiveWordBsConfig;

import com.huuc.pojo.SensitiveWordAllow;
import com.huuc.pojo.SensitiveWordDeny;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WordVO {
    List<SensitiveWordDeny> blackList;
    List<SensitiveWordAllow> whiteList;
}
