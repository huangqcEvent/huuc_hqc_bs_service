package com.huuc.service.recommend;



import java.util.*;
import java.util.stream.Collectors;

public class UserCF {
    private final Map<String, List<InteractionVO>> userItemMatrix;

    public UserCF(Map<String, List<InteractionVO>> userItemMatrix) {
        this.userItemMatrix = userItemMatrix;
    }

    public List<RecommendationVO> recommendPosts(String targetUserId, int topKUsers, int topKPosts) {
        Map<String, Double> userSimilarities = calculateUserSimilarities(targetUserId);
        List<String> topUsers = userSimilarities.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(topKUsers)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Map<String, Integer> targetItems = userItemMatrix.getOrDefault(targetUserId, Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(InteractionVO::getPostId, InteractionVO::getTotalScore));
        Map<String, Double> scores = new HashMap<>();
        Map<String, Double> simSums = new HashMap<>();

        for (String userId : topUsers) {
            double similarity = userSimilarities.get(userId);
            userItemMatrix.get(userId).forEach(interaction -> {
                String postId = interaction.getPostId();
                Integer postType = interaction.getPostType();
                int score = interaction.getTotalScore();
                String key = postId + "_" + postType;
                if (!targetItems.containsKey(postId)) {
                    scores.merge(key, score * similarity, Double::sum);
                    simSums.merge(key, similarity, Double::sum);
                }
            });
        }

        return scores.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue() / simSums.getOrDefault(e.getKey(), 1.0)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(topKPosts)
                .map(entry -> {
                    String[] parts = entry.getKey().split("_");
                    String postId = parts[0];
                    Integer postType = Integer.parseInt(parts[1]);
                    double score = entry.getValue();
                    return new RecommendationVO(postId, postType, score);
                })
                .collect(Collectors.toList());
    }

    private Map<String, Double> calculateUserSimilarities(String targetUserId) {
        List<InteractionVO> targetInteractions = userItemMatrix.get(targetUserId);
        if (targetInteractions == null) {
            return Collections.emptyMap();
        }

        return userItemMatrix.keySet().stream()
                .filter(userId -> !userId.equals(targetUserId))
                .collect(Collectors.toMap(
                        userId -> userId,
                        userId -> SimilarityCalculator.cosineSimilarity(targetInteractions, userItemMatrix.get(userId))));
    }
}
