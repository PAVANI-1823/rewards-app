package com.example.rewards_app.controller;


import com.example.rewards_app.model.Transaction;
import com.example.rewards_app.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
/**
 * Controller class for handling reward-related API requests.
 */
@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    /**
     * Calculates total reward points for a list of transactions.
     *
     * @param transactions List of transactions.
     * @return ResponseEntity with the total reward points.
     */
    @PostMapping("/calculate")
    public ResponseEntity<?> calculateRewards(@RequestBody List<Transaction> transactions) {
        // Validate input
        if (transactions == null || transactions.isEmpty()) {
            return ResponseEntity.badRequest().body("Transaction list cannot be empty");
        }
        // Calculate and return total points
        int totalPoints = rewardService.calculateTotalPoints(transactions.toArray(new Transaction[0]));
        return ResponseEntity.ok(totalPoints);
    }

    /**
     * Endpoint to calculate monthly rewards for a specific customer.
     *
     * @param customerId the ID of the customer
     * @param month the month in 'yyyy-MM' format
     * @return total reward points for the month
     */
    @GetMapping("/customer/{customerId}/monthly/{month}")
    public ResponseEntity<?> calculateMonthlyRewards(@PathVariable Long customerId, @PathVariable String month) {
        // Validate month format
        if (!month.matches("\\d{4}-\\d{2}")) {
            return ResponseEntity.badRequest().body("Invalid month format");
        }

        // Static data example
        List<Transaction> transactions = Arrays.asList(
                new Transaction(customerId, 120.0, month + "-01"),
                new Transaction(customerId, 90.0, month + "-15")
        );
        int totalPoints = rewardService.calculateTotalPoints(transactions.toArray(new Transaction[0]));
        return ResponseEntity.ok(totalPoints);
    }
}
