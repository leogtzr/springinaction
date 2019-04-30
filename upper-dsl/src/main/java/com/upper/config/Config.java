package com.upper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
public class Config {

    @Bean
    public IntegrationFlow up() {
        return IntegrationFlows
                .from(MessageChannels.direct("upper"))
                .<String, String>transform(String::toUpperCase)
                .get();
    }

}
