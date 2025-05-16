package com.company.gameplatform.game;
public abstract class Game {
    protected String name;
    protected boolean running;
    public Game(String name) {
        this.name = name;
        this.running = false;
    }
    public abstract void start();
    public abstract void stop();
    public String getName() { return name; }
    public boolean isRunning() { return running; }
}
