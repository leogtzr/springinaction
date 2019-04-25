package com.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.util.MimeTypeUtils;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        final MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();

        final DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        messageConverter.setContentTypeResolver(resolver);

        return messageConverter;
    }

}
