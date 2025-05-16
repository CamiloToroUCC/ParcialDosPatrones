package com.company.gameplatform.game;
public class FPSGame extends Game {
    public FPSGame(String name) { super(name); }
    @Override
    public void start() {
        if (!running) { System.out.println("[FPSGame] Loading maps and configuring controls for " + name); running = true; System.out.println("FPS game '" + name + "' has started."); }
        else { System.out.println("FPS game '" + name + "' is already running."); }
    }
    @Override
    public void stop() {
        if (running) { running = false; System.out.println("FPS game '" + name + "' has been stopped."); }
        else { System.out.println("FPS game '" + name + "' is not running."); }
    }
    public void shoot() { if (running) System.out.println("Shooting in " + name + "!"); else System.out.println("Cannot shoot; game '" + name + "' is not running."); }
    public void move() { if (running) System.out.println("Moving in " + name + "!"); else System.out.println("Cannot move; game '" + name + "' is not running."); }
}
