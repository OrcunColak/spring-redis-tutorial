package com.colak.springredistutorial.sortedset.leaderboard;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;

/**
 * See <a href="https://avi2507.medium.com/unlocking-the-power-of-redis-in-spring-boot-a-comprehensive-guide-with-jedis-1c7078557ae0">...</a>
 * Sorted similar to sets in which elements are ordered based on the score/rank.
 */
public class ZSetRepository<K, V> {

    private final K key;
    private final ZSetOperations<K, V> zSetOperations;

    public ZSetRepository(K key, ZSetOperations<K, V> zSetOperations) {
        this.key = key;
        this.zSetOperations = zSetOperations;
    }

    public Boolean add(V value, double score) {
        return zSetOperations.add(key, value, score);
    }

    public Set<ZSetOperations.TypedTuple<V>> getAll() {
        return zSetOperations.reverseRangeWithScores(key, 0L, -1L);
    }

    public Set<ZSetOperations.TypedTuple<V>> getTop10() {
        return zSetOperations.reverseRangeWithScores(key, 0, 10);
    }

    public Set<ZSetOperations.TypedTuple<V>> getTopN(long start, long end) {
        return zSetOperations.reverseRangeWithScores(key, start, end);
    }

    public Boolean deleteAll() {
        return zSetOperations.getOperations().delete(key);
    }
}
