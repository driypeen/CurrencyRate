package com.driypeen.CurrencyRate.service;

public interface CurrencyService {
    boolean hasRateIncreased(String currency);
    Double getCurrentRate(String currency);
    Double getPreviousRate(String currency);
}
