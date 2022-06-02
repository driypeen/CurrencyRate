package com.driypeen.CurrencyRate.service;

import com.driypeen.CurrencyRate.exception.NotFoundElementException;
import com.driypeen.CurrencyRate.feignClient.CurrencyClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String RATES_NODE = "rates";
    private CurrencyClient currencyClient;

    @Autowired
    public void setCurrencyClient(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    private double getRateFromNode(JsonNode rates, String currency) {
        if (rates == null || rates.get(currency) == null) {
            throw new NotFoundElementException("No data for this currency");
        }
        return rates.get(currency).asDouble();
    }

    @Override
    public Double getCurrentRate(String currency) {
        try {
            JsonNode node = mapper.readTree(currencyClient.getCurrentRate(System.getenv("CURRENCY_APP_ID"),
                    System.getenv("BASE_CURRENCY"),
                    currency));

            JsonNode rates = node.get(RATES_NODE);
            return getRateFromNode(rates, currency);
        } catch (JsonProcessingException e) {
            throw new NotFoundElementException("Exchange server error");
        }
    }

    @Override
    public Double getPreviousRate(String currency) {
        try {
            JsonNode node = mapper.readTree(currencyClient.getPreviousRate(System.getenv("CURRENCY_APP_ID"),
                    LocalDate.now().minusDays(1),
                    System.getenv("BASE_CURRENCY"),
                    currency));
            JsonNode rates = node.get(RATES_NODE);
            return getRateFromNode(rates, currency);
        } catch (JsonProcessingException e) {
            throw new NotFoundElementException("Exchange server error");
        }

    }

    @Override
    public boolean hasRateIncreased(String currency) {
        return getCurrentRate(currency) > getPreviousRate(currency);
    }
}
