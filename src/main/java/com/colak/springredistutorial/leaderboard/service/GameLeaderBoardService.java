package com.colak.springredistutorial.leaderboard.service;

import com.colak.springredistutorial.leaderboard.dto.GamerDTO;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * See <a href="https://avi2507.medium.com/unlocking-the-power-of-redis-in-spring-boot-a-comprehensive-guide-with-jedis-1c7078557ae0">...</a>
 * Sorted similar to sets in which elements are ordered based on the score/rank.
 */
@RequiredArgsConstructor
@Service
public class GameLeaderBoardService {

    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    private static final String LEADER_BOARD_NAME = "leaderboard";
    private static final long START = 0;
    private static final long END = 10;

    public List<GamerDTO> add(GamerDTO gamerDTO) {
        zSetOperations.add(LEADER_BOARD_NAME, gamerDTO.getName(), gamerDTO.getScore());

        Set<ZSetOperations.TypedTuple<String>> leaderBoard = zSetOperations.rangeWithScores(LEADER_BOARD_NAME, START, END);
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    public List<GamerDTO> getAll() {
        Set<ZSetOperations.TypedTuple<String>> leaderBoard = zSetOperations.reverseRangeWithScores(LEADER_BOARD_NAME, 0, -1);
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    public List<GamerDTO> getTop10() {
        Set<ZSetOperations.TypedTuple<String>> leaderBoard = zSetOperations.reverseRangeWithScores(LEADER_BOARD_NAME, START, END);
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

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
