package com.meesho;

import java.util.*;

public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final Random random = new Random();

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public String createGame(int boardSize, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, List<Integer> playerIds) {
        Set<Integer> players = new HashSet<>(playerIds);
        Game newGame = new Game(snakes, ladders, boardSize, players);
        gameRepository.save(newGame);
        return newGame.getId().toString();
    }

    /*
    return false
        - if player not part of this game
        - if the game is already ended
        - if the game id doesn't exist
        - if dice already allocated
    return true if hold dice is succeeded
    */
    @Override
    public Boolean holdDice(String gameId, int playerId) {
        System.out.println("\nCalling holdDice for player " + playerId);
        Game game = gameRepository.findAll().get(gameId);
        if (game == null) {
            return false;
        }
        if (game.getWinner() != null) {
            return false;
        }
        if (!game.getPlayerIds().contains(playerId)) {
            return false;
        }
        if (game.getCurrTurn() != null) {
            return false;
        }
        game.setCurrTurn(playerId);
        System.out.printf("Player %d has the dice now!!\n", playerId);
        return true;
    }

    /*
   return false
       - if any player who doesn't hold the dice calls this
       - if dice is not allocated
       - if the game is already ended
       - if the game id doesn't exist
   otherwise roll dice and move and return true
   */
    @Override
    public Boolean rollDiceAndMove(String gameId, int playerId) {
        Game game = gameRepository.findAll().get(gameId);
        if (game == null) {
            return false;
        }
        if (game.getWinner() != null) {
            return false;
        }
        if (game.getCurrTurn() == null || game.getCurrTurn() != playerId) {
            return false;
        }

        int diceValue = random.nextInt(6) + 1;
        System.out.println("Rolling dice.......");
        System.out.println("Curr position of player = " + game.getBoard().get(playerId));
        System.out.println("Curr dice roll = " + diceValue);

        Map<Integer, Integer> board = game.getBoard();
        int currPos = board.get(playerId);

        if (currPos + diceValue > game.getBoardSize() * game.getBoardSize()) {
            return false;
        }
        if (currPos + diceValue == game.getBoardSize() * game.getBoardSize()) {
            game.setWinner(playerId);
            gameRepository.findAll().put(gameId, game);
            System.out.printf("Player %s wins!!\n", playerId);
            return true;
        }
        int finalPos = posAfterEncounteringSnakesOrLadders(game, currPos + diceValue);
        if(game.getPlayerByCell().get(finalPos) != null){
            System.out.printf("Player %d cannot go to cell %d\n", playerId, finalPos);
            return false;
        }
        board.put(playerId, finalPos);
        game.setBoard(board);
        game.setCurrTurn(null);
        game.getPlayerByCell().put(currPos, null);
        game.getPlayerByCell().put(finalPos, playerId);

        gameRepository.findAll().put(gameId, game);
        System.out.printf("Player %s moves to index %s!!\n", playerId, finalPos);
        return true;
    }

    private int posAfterEncounteringSnakesOrLadders(Game game, int pos) {
        Map<Integer, Integer> snakes = game.getSnakes();
        Map<Integer, Integer> ladders = game.getLadders();

        if (snakes.get(pos) != null) {
            return snakes.get(pos);
        }
        if (ladders.get(pos) != null) {
            return ladders.get(pos);
        }
        return pos;
    }
}
