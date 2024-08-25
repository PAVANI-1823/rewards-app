package com.example.rewards_app.controller;


import com.example.rewards_app.model.Transaction;
import com.example.rewards_app.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @PostMapping("/calculate")
    public int calculateRewards(@RequestBody List<Transaction> transactions) {
        return rewardService.calculateTotalPoints(transactions.toArray(new Transaction[0]));
    }

    @GetMapping("/customer/{customerId}/monthly/{month}")
    public int calculateMonthlyRewards(@PathVariable Long customerId, @PathVariable String month) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(customerId, 120.0, month + "-01"),
                new Transaction(customerId, 90.0, month + "-15")
        );
        return rewardService.calculateTotalPoints(transactions.toArray(new Transaction[0]));
    }
}
