# RewardCalculator

A reward calculator api that calculates customers' rewards based on purchases.

## Introduction
### Main application
- Technologies used - Spring Boot, Spring Data JPA, Swagger
- Embedded Server - H2
- Configuration file - application.properties
- data.sql will be automatically populated during startup.

### Build
Using Maven, run `mvn package`

### Run 
Start the application by running [RewardCalculatorApplication class](src/main/java/com/dylan/rewardcalculator/RewardCalculatorApplication.java)

The other option is to run `java -jar <your jar file>`

### API
Only insert customer id, without specifying the end of period.

localhost:8080/customer/reward/2
```json
{
    "monthRewardVO": [
        {
            "monthYear": "2023-01",
            "reward": 200
        }
    ],
    "totalReward": 200
}
```

Inert customer id along with the end of period

localhost:8080/customer/reward/3?periodEnd=2021-09-01
```json
{
    "monthRewardVO": [
        {
            "monthYear": "2021-07",
            "reward": 230
        },
        {
            "monthYear": "2021-08",
            "reward": 250
        }
    ],
    "totalReward": 480
}
```
