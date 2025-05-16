package com.company.gameplatform.game;
public class RPGGame extends Game {
    public RPGGame(String name) { super(name); }
    @Override
    public void start() {
        if (!running) { System.out.println("[RPGGame] Initializing characters, quests, and environments for " + name); running = true; System.out.println("RPG game '" + name + "' has started."); }
        else { System.out.println("RPG game '" + name + "' is already running."); }
    }
    @Override
    public void stop() {
        if (running) { running = false; System.out.println("RPG game '" + name + "' has been stopped."); }
        else { System.out.println("RPG game '" + name + "' is not running."); }
    }
    public void attack() { if (running) System.out.println("Attacking in " + name + "!"); else System.out.println("Cannot attack; game '" + name + "' is not running."); }
    public void defend() { if (running) System.out.println("Defending in " + name + "!"); else System.out.println("Cannot defend; game '" + name + "' is not running."); }
}
