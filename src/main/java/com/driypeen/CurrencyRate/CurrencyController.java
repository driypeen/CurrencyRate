package com.driypeen.CurrencyRate;

import com.driypeen.CurrencyRate.feignClient.CurrencyClient;
import com.driypeen.CurrencyRate.service.CurrencyService;
import com.driypeen.CurrencyRate.service.GifServiceImpl;
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
    private GifServiceImpl gifService;
    private CurrencyService currencyService;
    private final static Logger LOGGER = LoggerFactory.getLogger(CurrencyClient.class);

    @Autowired
    public void setGifService(GifServiceImpl gifService, CurrencyService currencyService) {
        this.gifService = gifService;
        this.currencyService = currencyService;
    }

    @GetMapping("/rate")
    @ResponseBody
    public String getRate(@RequestParam("currency") String currencyName) {
        LOGGER.info("get rate of {}", currencyName);
        return currencyService.getCurrentRate(currencyName);
    }
}
