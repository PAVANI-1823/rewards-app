package com.example.rewards_app;

import com.example.rewards_app.model.Transaction;
import com.example.rewards_app.service.RewardService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardServiceTests {

    private final RewardService rewardService = new RewardService();

    @Test
    public void testCalculatePoints() {
        assertEquals(90, rewardService.calculatePoints(120.0));
        assertEquals(25, rewardService.calculatePoints(75.0));
        assertEquals(0, rewardService.calculatePoints(50.0));
    }

    @Test
    public void testCalculateTotalPoints() {
        Transaction[] transactions = {
                new Transaction(1L, 120.0, "2024-08-01"),
                new Transaction(1L, 75.0, "2024-08-15")
        };
        assertEquals(115, rewardService.calculateTotalPoints(transactions));
    }
}
