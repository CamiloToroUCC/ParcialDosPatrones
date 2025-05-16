package com.company.gameplatform.client;
import com.company.gameplatform.facade.GamePlatformFacade;
import com.company.gameplatform.factory.FPSGameFactory;
import com.company.gameplatform.factory.GameFactory;
import com.company.gameplatform.factory.RPGGameFactory;
import com.company.gameplatform.game.FPSGame;
import com.company.gameplatform.game.Game;
import com.company.gameplatform.game.RPGGame;
import com.company.gameplatform.manager.GameManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    private static GamePlatformFacade facade = new GamePlatformFacade();
    private static List<Game> games = new ArrayList<>();
    private static GameManager manager = GameManager.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        manager.loadResource("config", "Dynamic configuration for GamePlatformOnline");
        while (true) {
            showMainMenu();
            int option = getIntInput("Seleccione una opción: ");
            switch (option) {
                case 1:
                    listGames();
                    break;
                case 2:
                    createGame();
                    break;
                case 3:
                    joinGame();
                    break;
                case 4:
                    matchManagement();
                    break;
                case 5:
                    connectServer();
                    break;
                case 6:
                    rankingMenu();
                    break;
                case 7:
                    manager.releaseResource("config");
                    System.out.println("Recurso liberado.");
                    break;
                case 0:
                    System.out.println("Saliendo de la plataforma. ¡Hasta luego!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no reconocida.");
                    break;
            }
        }
    }
    private static void showMainMenu() {
        System.out.println("\n===================================");
        System.out.println("      Plataforma de Juegos Online  ");
        System.out.println("===================================");
        System.out.println("1. Listar juegos disponibles");
        System.out.println("2. Crear nuevo juego");
        System.out.println("3. Unirse a un juego");
        System.out.println("4. Gestionar partida");
        System.out.println("5. Conectarse a un servidor");
        System.out.println("6. Ranking (Actualizar/Ver)");
        System.out.println("7. Liberar recurso (Config)");
        System.out.println("0. Salir");
    }
    private static int getIntInput(String prompt) {
        int input = -1;
        System.out.print(prompt);
        try { input = Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Entrada inválida."); }
        return input;
    }
    private static void listGames() {
        if (games.isEmpty()) { System.out.println("No hay juegos disponibles."); }
        else {
            System.out.println("\n----- Juegos disponibles -----");
            for (int i = 0; i < games.size(); i++) {
                Game g = games.get(i);
                String genre = (g instanceof FPSGame) ? "FPS" : (g instanceof RPGGame ? "RPG" : "Unknown");
                System.out.println((i + 1) + ". " + g.getName() + " - " + genre + " (Running: " + g.isRunning() + ")");
            }
            System.out.println("------------------------------");
        }
    }
    private static void createGame() {
        System.out.println("\n=== Crear nuevo juego ===");
        int type = getIntInput("Ingrese el tipo de juego (1: FPS, 2: RPG): ");
        System.out.print("Ingrese el nombre del juego: ");
        String gameName = scanner.nextLine().trim();
        GameFactory factory;
        if (type == 1) { factory = new FPSGameFactory(); }
        else if (type == 2) { factory = new RPGGameFactory(); }
        else { System.out.println("Tipo de juego no reconocido."); return; }
        Game newGame = factory.createGame(gameName);
        games.add(newGame);
        System.out.println("Juego creado: " + newGame.getName());
    }
    private static void joinGame() {
        if (games.isEmpty()) { System.out.println("No hay juegos disponibles para unirse."); return; }
        listGames();
        int idx = getIntInput("Seleccione el número del juego a unirse: ");
        if (idx < 1 || idx > games.size()) { System.out.println("Selección inválida."); return; }
        Game selectedGame = games.get(idx - 1);
        if (!selectedGame.isRunning()) {
            System.out.print("El juego no está iniciado. ¿Desea iniciarlo? (S/N): ");
            String resp = scanner.nextLine().trim();
            if (resp.equalsIgnoreCase("S")) selectedGame.start();
            else { System.out.println("Operación cancelada."); return; }
        }
        System.out.println("Te has unido al juego: " + selectedGame.getName());
        gameActionsMenu(selectedGame);
    }
    private static void gameActionsMenu(Game game) {
        while (true) {
            System.out.println("\n--- Menú de acciones para " + game.getName() + " ---");
            if (game instanceof FPSGame) {
                System.out.println("1. Disparar");
                System.out.println("2. Moverse");
            } else if (game instanceof RPGGame) {
                System.out.println("1. Atacar");
                System.out.println("2. Defender");
            }
            System.out.println("0. Salir del juego");
            int action = getIntInput("Seleccione una acción: ");
            if (action == 0) { System.out.println("Saliendo del juego..."); break; }
            if (game instanceof FPSGame) {
                FPSGame fps = (FPSGame) game;
                if (action == 1) fps.shoot();
                else if (action == 2) fps.move();
                else System.out.println("Acción no reconocida.");
            } else if (game instanceof RPGGame) {
                RPGGame rpg = (RPGGame) game;
                if (action == 1) rpg.attack();
                else if (action == 2) rpg.defend();
                else System.out.println("Acción no reconocida.");
            }
        }
    }
    private static void matchManagement() {
        if (games.isEmpty()) { System.out.println("No hay juegos para gestionar partidas."); return; }
        System.out.println("\n=== Gestión de Partidas ===");
        listGames();
        int idx = getIntInput("Seleccione el número del juego: ");
        if (idx < 1 || idx > games.size()) { System.out.println("Selección inválida."); return; }
        Game selectedGame = games.get(idx - 1);
        System.out.println("1. Crear partida");
        System.out.println("2. Unirse a partida");
        int subOption = getIntInput("Seleccione una opción: ");
        if (subOption == 1) {
            int players = getIntInput("Ingrese el número de jugadores: ");
            facade.createMatch(selectedGame, players);
        } else if (subOption == 2) {
            System.out.print("Ingrese el tipo de partida (private/multiplayer): ");
            String matchType = scanner.nextLine().trim();
            facade.joinMatch(selectedGame, matchType);
        } else {
            System.out.println("Opción de partida no reconocida.");
        }
    }
    private static void connectServer() {
        System.out.println("\n=== Conexión a Servidor ===");
        System.out.print("Ingrese la dirección del servidor: ");
        String serverAddr = scanner.nextLine().trim();
        facade.connectToServer(serverAddr);
    }
    private static void rankingMenu() {
        System.out.println("\n=== Gestión de Rankings ===");
        System.out.println("1. Actualizar ranking");
        System.out.println("2. Ver rankings");
        int option = getIntInput("Seleccione una opción: ");
        if (option == 1) {
            System.out.print("Ingrese el nombre del jugador: ");
            String pname = scanner.nextLine().trim();
            int pts = getIntInput("Ingrese el puntaje: ");
            facade.updateRanking(pname, pts);
        } else if (option == 2) {
            facade.showRankings();
        } else {
            System.out.println("Opción de ranking no reconocida.");
        }
    }
}
