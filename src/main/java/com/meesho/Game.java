package com.meesho;

import lombok.Data;

import java.util.*;

@Data
public class Game {

    private UUID id;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private int boardSize;
    private Set<Integer> playerIds;
    private Integer winner;
    private Integer currTurn;
    private Map<Integer, Integer> board;
    private Map<Integer, Integer> playerByCell;

    public Game(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, int boardSize, Set<Integer> playerIds) {
        this.id = UUID.randomUUID();
        this.snakes = snakes;
        this.ladders = ladders;
        this.boardSize = boardSize;
        this.playerIds = playerIds;
        this.winner = null;
        this.currTurn = null;
        board = new HashMap<>();
        for(int player: playerIds){
            board.put(player, 0);
        }
        playerByCell = new HashMap<>();
    }
}
