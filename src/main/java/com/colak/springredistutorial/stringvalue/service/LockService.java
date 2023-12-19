package com.colak.springredistutorial.stringvalue.service;

import com.colak.springredistutorial.stringvalue.opsforvalue.OpsForValueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Service
public class LockService {

    private final OpsForValueService opsForValueService;

    public boolean performWithLock(String lockKey) throws InterruptedException {
        boolean result = false;
        if (Boolean.TRUE.equals(opsForValueService.setIfAbsent(lockKey,
                "my_value",
                15000,
                TimeUnit.MILLISECONDS))) {

            log.info("Lock acquired.");

            Thread.sleep(200);

            log.info("Operation completed.");

            // if you want, you can release lock.
            opsForValueService.delete(lockKey);

            result = true;
        } else {
            log.error("Failed to acquire lock. Resource is busy.");

        }
        return result;
    }
}
