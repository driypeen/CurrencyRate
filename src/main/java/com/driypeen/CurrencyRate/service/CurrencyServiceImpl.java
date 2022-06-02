package com.driypeen.CurrencyRate.service;

import com.driypeen.CurrencyRate.feignClient.CurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyClient currencyClient;

    @Autowired
    public void setCurrencyClient(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    @ResponseBody
    public String getCurrentRate(String currency) {
        return currencyClient.getLastRate(System.getenv("CURRENCY_APP_ID"),
                System.getenv("BASE_CURRENCY"),
                currency);
    }
}
