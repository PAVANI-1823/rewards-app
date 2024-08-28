package com.example.rewards_app;

import com.example.rewards_app.controller.RewardController;
import com.example.rewards_app.model.Transaction;
import com.example.rewards_app.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(RewardController.class)
public class RewardControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    @Test
    public void testCalculateRewards() throws Exception {
        when(rewardService.calculateTotalPoints(any(Transaction[].class))).thenReturn(115);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/rewards/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"customerId\":1, \"amount\":120, \"date\":\"2024-08-01\"}, {\"customerId\":1, \"amount\":75, \"date\":\"2024-08-15\"}]"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("115"));
    }

    @Test
    public void testCalculateMonthlyRewards() throws Exception {
        when(rewardService.calculateTotalPoints(any(Transaction[].class))).thenReturn(115);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rewards/customer/1/monthly/2024-08")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("115"));
    }

    @Test
    public void testCalculateMonthlyRewards_NoTransactions() throws Exception {
        when(rewardService.calculateTotalPoints(any(Transaction[].class))).thenReturn(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/rewards/customer/1/monthly/2024-08")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }

    @Test
    public void testCalculateRewards_InvalidAmount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/rewards/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"customerId\":1, \"amount\":\"invalid\", \"date\":\"2024-08-01\"}]"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid input data format"));
    }

    @Test
    public void testCalculateMonthlyRewards_InvalidMonthFormat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/rewards/customer/1/monthly/invalid-month")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid month format"));
    }


}
