package com.driypeen.CurrencyRate;

import com.driypeen.CurrencyRate.feignClient.CurrencyClient;
import com.driypeen.CurrencyRate.service.CurrencyService;
import com.driypeen.CurrencyRate.service.GifService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class CurrencyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyClient.class);
    private static final String GIF_UP = "rich";
    private static final String GIF_DOWN = "broke";

    private CurrencyService currencyService;
    private GifService gifService;

    @Autowired
    public void setCurrencyService(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Autowired
    public void setGifService(GifService gifService) {
        this.gifService = gifService;
    }

    @GetMapping("/rate")
    @ResponseBody
    private String compare(@RequestParam("currency") String currencyName) {
        LOGGER.info("Get rate of {}", currencyName);
        if (currencyService.hasRateIncreased(currencyName)) {
                return gifService.getGifByPhrase(GIF_UP);
        } else {
                return gifService.getGifByPhrase(GIF_DOWN);
        }
    }
}
