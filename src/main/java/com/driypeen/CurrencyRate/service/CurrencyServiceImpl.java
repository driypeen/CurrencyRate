package com.driypeen.CurrencyRate.service;

import com.driypeen.CurrencyRate.feignClient.CurrencyClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final ObjectMapper mapper = new ObjectMapper();

    private CurrencyClient currencyClient;

    @Autowired
    public void setCurrencyClient(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    public Double getCurrentRate(String currency) {
        try {
            JsonNode node = mapper.readTree(currencyClient.getLastRate(System.getenv("CURRENCY_APP_ID"),
                    System.getenv("BASE_CURRENCY"),
                    currency));
            return node.get("rates").get(currency).asDouble();
        } catch (Exception e) {
            return 0.0;
        }
    }

    @Override
    public Double getPreviousRate(String currency) {
        try {
            JsonNode node = mapper.readTree(currencyClient.getPreviousRate(System.getenv("CURRENCY_APP_ID"),
                    LocalDate.now().minusDays(1),
                    System.getenv("BASE_CURRENCY"),
                    currency));
            return node.get("rates").get(currency).asDouble();
        } catch (Exception e) {
            return 0.0;
        }
    }

    @Override
    public boolean hasRateIncreased(String currency) {
        double current =  getCurrentRate(currency);
        double previous = getPreviousRate(currency);

        return current > previous;
    }
}
