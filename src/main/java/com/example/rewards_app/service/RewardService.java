package com.example.rewards_app.service;

import com.example.rewards_app.model.Transaction;
import org.springframework.stereotype.Service;

/**
 * Service class for handling reward calculations.
 */
@Service
public class RewardService {

    /**
     * Calculates reward points based on the transaction amount.
     *
     * @param amount the transaction amount
     * @return the calculated points
     */
    public int calculatePoints(double amount) {
        int points = 0;

        if (amount > 100) {
            points += 2 * (amount - 100);
            points += 1 * 50;
        } else if (amount > 50) {
            points += 1 * (amount - 50);
        }

        return points;
    }

    /**
     * Calculates total reward points for an array of transactions.
     *
     * @param transactions array of transactions
     * @return the total reward points
     */
    public int calculateTotalPoints(Transaction[] transactions) {
        int totalPoints = 0;
        for (Transaction transaction : transactions) {
            totalPoints += calculatePoints(transaction.getAmount());
        }
        return totalPoints;
    }
}
