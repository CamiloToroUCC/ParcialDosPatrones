package com.company.gameplatform.factory;
import com.company.gameplatform.game.Game;
import com.company.gameplatform.game.RPGGame;
public class RPGGameFactory extends GameFactory {
    @Override
    public Game createGame(String name) { return new RPGGame(name); }
}
