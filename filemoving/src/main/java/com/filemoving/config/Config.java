package com.filemoving.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
public class Config {

    private static final String INPUT_DIR = "/tmp/input";
    private static final String OUTPUT_DIR = "/tmp/output";

    @Bean
    public IntegrationFlow fileMover() {
        return IntegrationFlows
                .from(sourceDirectory(), configurer -> configurer.poller(Pollers.fixedDelay(1000)))
                // .filter(onlyTxts())
                .publishSubscribeChannel(msg -> {
                    System.out.println("Alv ... ");
                })
                .log()
                .handle(targetDirectory())
                .get()
                ;
    }

    @Bean
    public GenericSelector<File> onlyTxts() {
        return source -> source.getName().endsWith(".txt");
    }

    @Bean
    public MessageSource<File> sourceDirectory() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(INPUT_DIR));
        return messageSource;
    }

    @Bean
    public MessageHandler targetDirectory() {
        final FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setExpectReply(false); // end of pipeline, reply not needed
        return handler;
    }

}
