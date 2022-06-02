package com.driypeen.CurrencyRate.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface CurrencyService {
    boolean hasRateIncreased(String currency);
    Double getCurrentRate(String currency) throws JsonProcessingException;
    Double getPreviousRate(String currency) throws JsonProcessingException;
}
