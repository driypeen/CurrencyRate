package com.driypeen.CurrencyRate.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApplicationConfig {
    private String serverPort;
    private String baseCurrency;
}
