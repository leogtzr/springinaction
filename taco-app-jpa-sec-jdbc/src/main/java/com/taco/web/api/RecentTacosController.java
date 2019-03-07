package com.taco.web.api;

import com.taco.data.TacoRepository;
import com.taco.domain.Taco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosController {

    private TacoRepository tacoRepository;

    public RecentTacosController(final TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public ResponseEntity<Resources<TacoResource>> recentTacos() {
        final PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());

        final Iterable<Taco> tacosIt = tacoRepository.findAll();
        final List<Taco> tacos = new ArrayList<>();
        tacosIt.forEach(tacos::add);

        final List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
        final Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
        recentResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }

}
