package com.colak.springredistutorial.controller;

import com.colak.springredistutorial.service.LockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lock")
public class LockController {

    private final LockService lockService;

    @GetMapping("/perform/{lockKey}")
    public String performOperation(@PathVariable String lockKey) throws InterruptedException {
        String result = "Operation failed";
        if (lockService.performWithLock(lockKey)) {
            result = "Operation completed";
        }
        return result;
    }
}
