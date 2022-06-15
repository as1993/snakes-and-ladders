package com.meesho;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameApplication {
    public static void main(String[] args) {
        GameRepository repository = new GameRepositoryImpl();
        GameService gameService = new GameServiceImpl(repository);

        int boardSize = 4;
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(11, 2);
        snakes.put(8, 5);

        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(10, 14);
        ladders.put(4, 9);

        List<Integer> players = Arrays.asList(1, 2, 3);

        String gameId = gameService.createGame(boardSize, snakes, ladders, players);
        System.out.println("\n\n-------------------------------------------------------------");
        System.out.printf("Game %s started\n", gameId);

        System.out.println(gameService.holdDice(gameId, 0));

        System.out.println(gameService.holdDice(gameId, 1));
        System.out.println(gameService.rollDiceAndMove(gameId, 1));

        System.out.println(gameService.holdDice(gameId, 2));
        System.out.println(gameService.rollDiceAndMove(gameId, 2));

        System.out.println(gameService.holdDice(gameId, 2));
        System.out.println(gameService.rollDiceAndMove(gameId, 2));

        System.out.println(gameService.holdDice(gameId, 2));
        System.out.println(gameService.rollDiceAndMove(gameId, 2));

        System.out.println(gameService.holdDice(gameId, 1));
        System.out.println(gameService.rollDiceAndMove(gameId, 1));

        System.out.println(gameService.holdDice(gameId, 1));
        System.out.println(gameService.rollDiceAndMove(gameId, 1));

        System.out.println(gameService.holdDice(gameId, 1));
        System.out.println(gameService.rollDiceAndMove(gameId, 1));

//        System.out.println(gameService.rollDiceAndMove(gameId, 1));
//        System.out.println(gameService.rollDiceAndMove(gameId, 1));
//        System.out.println(gameService.rollDiceAndMove(gameId, 1));
        System.out.println("\n\n-------------------------------------------------------------\n\n");
    }
}
