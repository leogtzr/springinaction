package com.taco.config;

import com.taco.domain.Taco;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

@Configuration
public class GeneralConfig {

    @Bean
    public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(final EntityLinks links) {
        return resource -> {
            resource.add(
                    links.linkFor(Taco.class)
                            .slash("recent")
                            .withRel("recents"));
            return resource;
        };
    }

}
