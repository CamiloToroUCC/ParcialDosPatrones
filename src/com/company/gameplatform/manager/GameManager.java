package com.company.gameplatform.manager;
import java.util.HashMap;
import java.util.Map;
public class GameManager {
    private static volatile GameManager instance;
    private Map<String, Object> resources;
    private GameManager() { resources = new HashMap<>(); }
    public static GameManager getInstance() {
        if (instance == null) {
            synchronized (GameManager.class) {
                if (instance == null) instance = new GameManager();
            }
        }
        return instance;
    }
    public void loadResource(String key, Object resource) {
        if (key != null && resource != null) {
            resources.put(key, resource);
            System.out.println("[GameManager] Resource loaded: " + key);
        }
    }
    public Object getResource(String key) { return resources.get(key); }
    public void releaseResource(String key) {
        resources.remove(key);
        System.out.println("[GameManager] Resource released: " + key);
    }
}
