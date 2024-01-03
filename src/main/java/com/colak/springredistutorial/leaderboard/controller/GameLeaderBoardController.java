package com.colak.springredistutorial.leaderboard.controller;

import com.colak.springredistutorial.leaderboard.dto.GamerDTO;
import com.colak.springredistutorial.leaderboard.service.GameLeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/gameleaderboard")
public class GameLeaderBoardController {

    private final GameLeaderBoardService gameLeaderBoardService;

    @GetMapping("/getAll")
    public List<GamerDTO> getTop10() {
        return gameLeaderBoardService.getTop10();
    }

    @PostMapping("/add")
    public List<GamerDTO> add(@RequestBody GamerDTO gamerDTO) {
        return gameLeaderBoardService.add(gamerDTO);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        gameLeaderBoardService.deleteAll();
    }

}
