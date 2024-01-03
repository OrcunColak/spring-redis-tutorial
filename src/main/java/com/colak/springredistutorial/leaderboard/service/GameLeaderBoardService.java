package com.colak.springredistutorial.leaderboard.service;

import com.colak.springredistutorial.leaderboard.dto.GamerDTO;

import java.util.List;

public interface GameLeaderBoardService {

    List<GamerDTO> add(GamerDTO gamerDTO);

    List<GamerDTO> getAll();

    List<GamerDTO> getTop10();

    void deleteAll();
}
