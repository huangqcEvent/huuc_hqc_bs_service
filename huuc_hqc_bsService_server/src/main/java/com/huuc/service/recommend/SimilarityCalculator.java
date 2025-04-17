package com.huuc.service.recommend;



import java.util.*;
import java.util.stream.Collectors;

public class SimilarityCalculator {
    public static double cosineSimilarity(List<InteractionVO> vec1, List<InteractionVO> vec2) {
        Map<String, Integer> map1 = vec1.stream()
                .collect(Collectors.toMap(vo -> vo.getPostId() + "_" + vo.getPostType(), InteractionVO::getTotalScore));
        Map<String, Integer> map2 = vec2.stream()
                .collect(Collectors.toMap(vo -> vo.getPostId() + "_" + vo.getPostType(), InteractionVO::getTotalScore));

        Set<String> commonKeys = new HashSet<>(map1.keySet());
        commonKeys.retainAll(map2.keySet());
        if (commonKeys.isEmpty()) return 0.0;

        double dotProduct = 0.0, norm1 = 0.0, norm2 = 0.0;
        for (String key : commonKeys) {
            int v1 = map1.get(key);
            int v2 = map2.get(key);
            dotProduct += v1 * v2;
            norm1 += v1 * v1;
            norm2 += v2 * v2;
        }
        return (norm1 == 0 || norm2 == 0) ? 0 : dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }
}
