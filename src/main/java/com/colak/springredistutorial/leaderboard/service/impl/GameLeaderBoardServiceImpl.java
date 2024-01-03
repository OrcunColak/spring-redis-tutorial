package com.colak.springredistutorial.leaderboard.service.impl;

import com.colak.springredistutorial.leaderboard.dto.GamerDTO;
import com.colak.springredistutorial.leaderboard.service.GameLeaderBoardService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class GameLeaderBoardServiceImpl implements GameLeaderBoardService {

    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    private static final String LEADER_BOARD_NAME = "leaderboard";
    private static final long START = 0;
    private static final long END = 10;

    @Override
    public List<GamerDTO> add(GamerDTO gamerDTO) {
        zSetOperations.add(LEADER_BOARD_NAME, gamerDTO.getName(), gamerDTO.getScore());

        Set<ZSetOperations.TypedTuple<String>> leaderBoard = zSetOperations.rangeWithScores(LEADER_BOARD_NAME, START, END);
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    @Override
    public List<GamerDTO> getAll() {
        Set<ZSetOperations.TypedTuple<String>> leaderBoard = zSetOperations.reverseRangeWithScores(LEADER_BOARD_NAME, 0, -1);
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    @Override
    public List<GamerDTO> getTop10() {
        Set<ZSetOperations.TypedTuple<String>> leaderBoard = zSetOperations.reverseRangeWithScores(LEADER_BOARD_NAME, START, END);
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    @Override
    public void deleteAll() {
        zSetOperations.getOperations().delete(LEADER_BOARD_NAME);
    }

    private List<GamerDTO> mapLeaderBoardToList(Set<ZSetOperations.TypedTuple<String>> leaderBoard) {
        List<GamerDTO> resultList = new ArrayList<>(leaderBoard.size());
        for (ZSetOperations.TypedTuple<String> tuple : leaderBoard) {
            String userId = tuple.getValue();
            GamerDTO gamerDTO = new GamerDTO();
            gamerDTO.setName(userId);
            gamerDTO.setScore(tuple.getScore());
            resultList.add(gamerDTO);
        }
        return resultList;
    }
}
