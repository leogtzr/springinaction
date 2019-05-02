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

    @Bean
    public IntegrationFlow print() {
        return IntegrationFlows
                .from(MessageChannels.direct("sysout"))
                .handle(msg -> {
                    System.out.println("Message payload: " + msg.getPayload());
                })
                .get();
    }

    @Bean
    public IntegrationFlow lowerFlow() {
        return IntegrationFlows
                .from("lower")
                .<String, String>transform(String::toLowerCase)
                .channel("decorate")
                .get();
    }

    @Bean
    public IntegrationFlow decoaretFlow() {
        return IntegrationFlows
                .from("decorate")
                .<String, String>transform(s -> "[" + s + "]")
                .get();
    }

}
