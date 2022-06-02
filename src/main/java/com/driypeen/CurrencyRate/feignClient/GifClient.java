package com.driypeen.CurrencyRate.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gifClient", url = "${GIF_URL}")
public interface GifClient {
    @RequestMapping(path = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    String getRichGif(@RequestParam("api_key") String key,
                      @RequestParam("q") String q,
                      @RequestParam("offset") int offset,
                      @RequestParam("limit") int limit);
}
