package com.driypeen.CurrencyRate.service;

import com.driypeen.CurrencyRate.exception.NotFoundElementException;
import com.driypeen.CurrencyRate.feignClient.CurrencyClient;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
public class CurrencyServiceTest {
    @InjectMocks
    CurrencyServiceImpl currencyService;

    @Mock
    CurrencyClient currencyClient;

    static JSONObject rate150 = new JSONObject();
    static JSONObject rate100 = new JSONObject();

    @BeforeAll
    static void setRates() {
        rate150.put("rates", Collections.singletonMap("RUB", "150.0"));
        rate100.put("rates", Collections.singletonMap("RUB", "100.0"));
    }

    @Test
    void rateUp() {
        Mockito.when(currencyClient.getPreviousRate(Mockito.anyString(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn(String.valueOf(rate100));

        Mockito.when(currencyClient.getCurrentRate(Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn(String.valueOf(rate150));

        Assertions.assertTrue(currencyService.hasRateIncreased("RUB"));
    }

    @Test
    void rateDown() {
        Mockito.when(currencyClient.getPreviousRate(Mockito.anyString(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn(String.valueOf(rate150));

        Mockito.when(currencyClient.getCurrentRate(Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn(String.valueOf(rate100));

        Assertions.assertFalse(currencyService.hasRateIncreased("RUB"));
    }

    @Test
    void serverError() {
        Mockito.when(currencyClient.getPreviousRate(Mockito.anyString(),
                Mockito.any(),
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn("incorrect string");

        Mockito.when(currencyClient.getCurrentRate(Mockito.anyString(),
                Mockito.anyString(),
                Mockito.anyString())
        ).thenReturn("incorrect string #2");

        Exception exception = Assertions.assertThrows(NotFoundElementException.class,
                () -> currencyService.hasRateIncreased("currency"));

        String expectedMessage = "Exchange server error";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
