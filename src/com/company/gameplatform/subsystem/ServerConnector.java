package com.company.gameplatform.subsystem;
public class ServerConnector {
    private boolean connected = false;
    public void connect(String serverAddress) {
        if (serverAddress != null && !serverAddress.trim().isEmpty()) { connected = true; System.out.println("Connected successfully to server: " + serverAddress); }
        else { System.out.println("Server address is invalid. Connection failed."); }
    }
    public boolean isConnected() { return connected; }
}
