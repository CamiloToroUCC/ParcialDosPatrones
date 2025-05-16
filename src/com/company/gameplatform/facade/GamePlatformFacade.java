package com.company.gameplatform.facade;
import com.company.gameplatform.game.Game;
import com.company.gameplatform.subsystem.MatchManager;
import com.company.gameplatform.subsystem.RankingManager;
import com.company.gameplatform.subsystem.ServerConnector;
public class GamePlatformFacade {
    private MatchManager matchManager;
    private ServerConnector serverConnector;
    private RankingManager rankingManager;
    public GamePlatformFacade() {
        this.matchManager = new MatchManager();
        this.serverConnector = new ServerConnector();
        this.rankingManager = new RankingManager();
    }
    public void createMatch(Game game, int players) { matchManager.createMatch(game, players); }
    public void joinMatch(Game game, String matchType) { matchManager.joinMatch(game, matchType); }
    public void connectToServer(String serverAddress) { serverConnector.connect(serverAddress); }
    public void updateRanking(String player, int score) { rankingManager.updateRanking(player, score); }
    public void showRankings() { rankingManager.displayRankings(); }
}
