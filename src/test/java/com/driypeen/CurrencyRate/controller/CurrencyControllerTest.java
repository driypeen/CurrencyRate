package com.driypeen.CurrencyRate.controller;

import com.driypeen.CurrencyRate.controller.CurrencyController;
import com.driypeen.CurrencyRate.service.CurrencyServiceImpl;
import com.driypeen.CurrencyRate.service.GifService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CurrencyControllerTest {
    @InjectMocks
    CurrencyController currencyController;

    @Mock
    CurrencyServiceImpl currencyService;

    @Mock
    GifService gifService;

    @Test
    void gifUp() {
        Mockito.when(currencyService.hasRateIncreased(Mockito.anyString()))
                .thenReturn(true);

        currencyController.compare("currencyName");
        Mockito.verify(gifService).getGifByPhrase("rich");
    }

    @Test
    void gifDown() {
        Mockito.when(currencyService.hasRateIncreased(Mockito.anyString()))
                .thenReturn(false);

        currencyController.compare("currencyName");
        Mockito.verify(gifService).getGifByPhrase("broke");
    }
}
