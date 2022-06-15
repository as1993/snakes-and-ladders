package com.meesho;

import java.util.HashMap;
import java.util.Map;

public class GameRepositoryImpl implements GameRepository {

    private Map<String, Game> allGames;

    public GameRepositoryImpl(){
        allGames = new HashMap<>();
    }

    @Override
    public void save(Game newGame) {
        allGames.put(newGame.getId().toString(), newGame);
    }

    @Override
    public Map<String, Game> findAll() {
        return allGames;
    }


}
