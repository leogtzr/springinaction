package com.taco.web.api;

import com.taco.data.TacoRepository;
import com.taco.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
//import org.springframework.hateoas.Link;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController("tamal1")
@RequestMapping(path = "/design", produces = "application/json")                     // Accept: "application/json"
// @RequestMapping(path = "/design", produces = {"application/json", "text/xml")     // Accept: "application/json"
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private TacoRepository tacoRepository;

    @Autowired
    private EntityLinks entityLinks;

    public DesignTacoController(final TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

//    @GetMapping("/recent")
//    public Iterable<Taco> recentTacos() {
//        final PageRequest page = PageRequest.of(
//            0, 12, Sort.by("createdAt").descending()
//        );
//        return tacoRepository.findAll(page).getContent();
//    }

//    @GetMapping("/recent")
//    public Resources<Resource<Taco>> recentTacos() {
//        final PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//        final Collection<Taco> tacos = tacoRepository.findAll(page).getContent();
//        final Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
//
//        // recentResources.add(new Link("http://localhost:8080/design/recent", "recents"));
////        recentResources.add(
////                ControllerLinkBuilder.linkTo(DesignTacoController.class)
////                        .slash("recent")                //          /recent
////                        .withRel("recents")
////);
//        //final Resources<Resource<Taco>> recentResources = Resources.wrap(tacos);
//        recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos())
//                .withRel("recents")
//        );
//
//        return recentResources;
//    }

    @GetMapping("/recent")
    public Resources<TacoResource> recentTacos() {
//        final PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//        Collection<Taco> tacos = tacoRepository.findAllPage(page).getContent();

        final Iterable<Taco> tacosIt = tacoRepository.findAll();
        final List<Taco> tacos = new ArrayList<>();
        tacosIt.forEach(tacos::add);

        final List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
        final Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);

        recentResources.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos()).withRel("recents")
        );

        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") final Long id) {
        final Optional<Taco> maybeTaco = tacoRepository.findById(id);
        if (maybeTaco.isPresent()) {
            // return maybeTaco.get();
            return new ResponseEntity<>(maybeTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")             // Content-Type: application/json
    @ResponseStatus(HttpStatus.CREATED)
    // CREATED (201) indicates that not only the request was succesfull but it also created a resource
    public Taco postTaco(@RequestBody final Taco taco) {
        return tacoRepository.save(taco);
    }

//    @GetMapping("/{id}")
//    public Taco tacoById(@PathVariable("id") final Long id) {
//        final Optional<Taco> maybeTaco = tacoRepository.findById(id);
//        if (maybeTaco.isPresent()) {
//            return maybeTaco.get();
//        }
//        return null;
//    }
}
