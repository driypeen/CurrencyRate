package com.driypeen.CurrencyRate.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(value = "currencyClient", url = "${CURRENCY_URL}")
public interface CurrencyClient {
    @RequestMapping(path = "/api/latest.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCurrentRate(@RequestParam("app_id") String appId,
                          @RequestParam("base") String baseCurrency,
                          @RequestParam("symbols") String currency);

    @RequestMapping(path = "/api/historical/{date}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getPreviousRate(@RequestParam("app_id") String appId,
                       @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                       @RequestParam("base") String baseCurrency,
                       @RequestParam("symbols") String currency);
}
