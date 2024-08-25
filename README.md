# Rewards Application

## Overview

This Spring Boot application calculates reward points based on customer transactions. 
It provides a RESTful API to calculate rewards and simulate asynchronous data fetching.

## Setup

1. Clone the repository:
   git clone https://github.com/yourusername/rewards-app.git
2. cd rewards-app

3. ./mvnw spring-boot:run
4. Junit Test cases implemented
5. Rest API mock test implemented
6. API Endpoints
   Calculate Rewards

    Endpoint: /api/rewards/calculate
    Method: POST
   Request Body:
   [
   {"customerId":1, "amount":120, "date":"2024-08-01"},
   {"customerId":1, "amount":75, "date":"2024-08-15"}
   ]
   Response: Total reward points
7. Calculate Monthly Rewards
   Endpoint: /api/rewards/customer/{customerId}/monthly/{month}
   Method: GET
   Response: Total reward points for the given month
8. Rewards calculation logic:
    Amount more than $100
       2 points per dollar over $100
       1 point per dollar between$50 and $100
    Amount less than $50
          1 point per dollar between$50 and $100
