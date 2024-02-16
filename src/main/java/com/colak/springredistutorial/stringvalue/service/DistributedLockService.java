package com.colak.springredistutorial.stringvalue.service;

import com.colak.springredistutorial.stringvalue.opsforvalue.OpsForValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redission client provides lock API
 * See <a href="https://gurselgazii.medium.com/implementing-distributed-locks-in-spring-boot-with-redisson-2967149bcb7c">...</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DistributedLockService {

    private final OpsForValueService opsForValueService;

    public boolean acquireLock(String lockKey, String value, long timeout, TimeUnit unit) {
        return opsForValueService.setIfAbsent(lockKey, value, timeout, unit);
    }

    public void releaseLock(String lockKey) {
        opsForValueService.delete(lockKey);
    }

    public boolean performWithLock(String lockKey) throws InterruptedException {
        boolean result = false;
        if (acquireLock(lockKey,
                "my_value",
                15000,
                TimeUnit.MILLISECONDS)) {

            log.info("Lock acquired.");

            Thread.sleep(200);

            log.info("Operation completed.");

            // if you want, you can release lock.
            releaseLock(lockKey);

            result = true;
        } else {
            log.error("Failed to acquire lock. Resource is busy.");

        }
        return result;
    }
}
