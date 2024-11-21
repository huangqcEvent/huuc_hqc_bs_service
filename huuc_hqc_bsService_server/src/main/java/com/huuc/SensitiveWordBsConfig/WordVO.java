package com.huuc.SensitiveWordBsConfig;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WordVO {
    List<String> blackList;
    List<String> whiteList;
}
