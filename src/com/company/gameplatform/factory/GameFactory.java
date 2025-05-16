package com.company.gameplatform.factory;
import com.company.gameplatform.game.Game;
public abstract class GameFactory {
    public abstract Game createGame(String name);
}
