package com.company.gameplatform.factory;
import com.company.gameplatform.game.FPSGame;
import com.company.gameplatform.game.Game;
public class FPSGameFactory extends GameFactory {
    @Override
    public Game createGame(String name) { return new FPSGame(name); }
}
