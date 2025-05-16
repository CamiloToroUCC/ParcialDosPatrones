package com.company.gameplatform.subsystem;
import java.util.*;
public class RankingManager {
    private Map<String, Integer> rankings = new HashMap<>();
    public void updateRanking(String player, int score) {
        if (score >= 0) { rankings.put(player, score); System.out.println("Ranking updated: " + player + " -> " + score); }
        else { System.out.println("Score must be positive."); }
    }
    public void displayRankings() {
        if (rankings.isEmpty()) { System.out.println("No rankings available."); return; }
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(rankings.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        System.out.println("---- Rankings ----");
        for (Map.Entry<String, Integer> entry : sorted) { System.out.println(entry.getKey() + " : " + entry.getValue()); }
    }
}
