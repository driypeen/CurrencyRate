package com.driypeen.CurrencyRate.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "currencyClient", url = "${CURRENCY_URL}")
public interface CurrencyClient {
    @RequestMapping(path = "/api/latest.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getLastRate(@RequestParam("app_id") String appId,
                       @RequestParam("base") String baseCurrency,
                       @RequestParam("symbols") String code);
}
