package com.driypeen.CurrencyRate.CurrencyClient;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class FeignClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(FeignClient.class);

    @GetMapping("/rate/{currency}")
    @ResponseBody
    public String getRate(@PathVariable("currency") String currencyName) {
        String cur = System.getenv("BASE_CURRENCY");
        LOGGER.info("get rate of {}, now base currency is {}", currencyName, cur);
        return cur;
    }
}
