package com.company.gameplatform.subsystem;
import com.company.gameplatform.game.Game;
public class MatchManager {
    public void createMatch(Game game, int players) {
        if (game.isRunning())
            System.out.println("Match created for game: " + game.getName() + " with " + players + " players.");
        else
            System.out.println("Cannot create match: Game '" + game.getName() + "' is not running.");
    }
    public void joinMatch(Game game, String matchType) {
        if (game.isRunning())
            System.out.println("Joined " + matchType + " match for game: " + game.getName());
        else
            System.out.println("Cannot join match: Game '" + game.getName() + "' is not running.");
    }
}
