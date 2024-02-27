package com.colak.springredistutorial.sortedset.leaderboard.service;

import com.colak.springredistutorial.sortedset.leaderboard.ZSetRepository;
import com.colak.springredistutorial.sortedset.leaderboard.dto.GamerDTO;
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
@Service
public class GameLeaderBoardService {

    private final ZSetRepository<String, String> repository;

    private static final String LEADER_BOARD_NAME = "leaderboard";


    public GameLeaderBoardService(RedisTemplate<String, String> redisTemplate) {
        repository = new ZSetRepository<>(LEADER_BOARD_NAME, redisTemplate.opsForZSet());
    }

    public List<GamerDTO> add(GamerDTO gamerDTO) {
        repository.add(gamerDTO.getName(), gamerDTO.getScore());

        Set<ZSetOperations.TypedTuple<String>> leaderBoard = repository.getTop10();
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    public List<GamerDTO> getAll() {
        Set<ZSetOperations.TypedTuple<String>> leaderBoard = repository.getAll();
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    public List<GamerDTO> getTop10() {
        Set<ZSetOperations.TypedTuple<String>> leaderBoard = repository.getTop10();
        assert leaderBoard != null;
        return mapLeaderBoardToList(leaderBoard);
    }

    public Boolean deleteAll() {
        return repository.deleteAll();
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
