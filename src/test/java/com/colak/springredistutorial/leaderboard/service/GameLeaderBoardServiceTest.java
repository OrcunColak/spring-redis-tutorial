package com.colak.springredistutorial.leaderboard.service;

import com.colak.springredistutorial.leaderboard.dto.GamerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GameLeaderBoardServiceTest {

    @Autowired
    private GameLeaderBoardService gameLeaderBoardService;


    @Test
    void testAdd() {
        GamerDTO gamer10 = new GamerDTO("name10", 10.0);
        GamerDTO gamer9 = new GamerDTO("name9", 9.0);
        List<GamerDTO> expectedList = List.of(gamer10, gamer9);

        gameLeaderBoardService.add(gamer10);
        List<GamerDTO> result = gameLeaderBoardService.add(gamer9);
        Assertions.assertEquals(expectedList, result);

        result = gameLeaderBoardService.getAll();
        Assertions.assertEquals(expectedList, result);

        result = gameLeaderBoardService.getTop10();
        Assertions.assertEquals(expectedList, result);

        gameLeaderBoardService.deleteAll();
    }
}
