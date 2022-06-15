package com.meesho;

import java.util.Map;

public interface GameRepository {
    void save(Game newGame);

    Map<String, Game> findAll();
}
