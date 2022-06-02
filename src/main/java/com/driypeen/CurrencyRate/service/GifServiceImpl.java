package com.driypeen.CurrencyRate.service;

import com.driypeen.CurrencyRate.feignClient.GifClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class GifServiceImpl implements GifService {
    private GifClient gifClient;

    @Autowired
    public void setGifClient(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    public String getGifByPhrase(String phrase) {
        return gifClient.getRichGif(System.getenv("GIF_API_KEY"), phrase,
                ThreadLocalRandom.current().nextInt(1, 5000), 1);
    }
}
