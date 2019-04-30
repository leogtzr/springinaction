package com.filegateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.*;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;

@Configuration
public class Config {

//    @Profile("javaconfig")
//    @Bean
//    @Transformer(inputChannel="textInChannel",
//            outputChannel="fileWriterChannel")
//    public GenericTransformer<String, String> upperCaseTransformer() {
//        return text -> text.toUpperCase();
//    }
//
//    @Profile("javaconfig")
//    @Bean
//    @ServiceActivator(inputChannel="fileWriterChannel")
//    public FileWritingMessageHandler fileWriter() {
//        FileWritingMessageHandler handler =
//                new FileWritingMessageHandler(new File("/tmp/sia5/files"));
//        handler.setExpectReply(false);
//        handler.setFileExistsMode(FileExistsMode.APPEND);
//        handler.setAppendNewLine(true);
//        return handler;
//    }

    // DSL config:
    @Profile("javadsl")
    @Bean
    public IntegrationFlow fileWriterFlow() {
        return IntegrationFlows
                .from(MessageChannels.direct("textInChannel"))
                .<String, String>transform(String::toUpperCase)
                .handle(
                        Files.outboundAdapter(new File("/tmp/files/"))
                        .fileExistsMode(FileExistsMode.APPEND)
                        .appendNewLine(true)
                )
                .get();
    }

//    @Bean
//    public IntegrationFlow upcase() {
//        return flow ->
//                //flow.channel("upcase")
//                flow.split()
//                .<String, String>transform(String::toUpperCase)
//                .aggregate()
//                ;
//    }

//    @Profile("random")
//    @Bean
//    public GenericHandler<Long> randomHandler() {
//        return (aLong, messageHeaders) -> aLong * 5L;
//    }
//
//    @Profile("random")
//    @Bean
//    public IntegrationFlow randomFlow() {
//        return IntegrationFlows
//                //from("random", c -> c.poller(Pollers.fixedDelay(1_000L)))
//                //from("random")
//                //from(this::alv, c -> c.poller(Pollers.fixedDelay(1_000)))
//                //.channel("random")
//                //.handle(randomHandler())
//                .from(MessageChannels.direct("random"))
//                .handle(Long.class, (p, h) -> p)
//                .log()
//                .get();
//    }
//
//    @Profile("random")
//    @Bean
//    public GenericMessage<Long> integerMessageSource() {
//        GenericMessage<Long> integerGenericMessage = new GenericMessage<>(12L);
//        return integerGenericMessage;
//    }
//
//    @Profile("random")
//    @Bean
//    public Message<Long> alv() {
//        return MessageBuilder.
//                withPayload(5L).build();
//    }

    @Bean
    public IntegrationFlow up() {
        return IntegrationFlows
                .from(MessageChannels.direct("upper"))
                .<String, String>transform(t -> t.toUpperCase())
                .get();
    }

}
