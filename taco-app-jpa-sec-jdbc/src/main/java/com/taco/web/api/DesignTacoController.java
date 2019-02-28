package com.taco.web.api;

import com.taco.data.TacoRepository;
import com.taco.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Optional;

@RestController
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

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        final PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending()
        );
        return tacoRepository.findAll(page).getContent();
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

//    @GetMapping("/{id}")
//    public Taco tacoById(@PathVariable("id") final Long id) {
//        final Optional<Taco> maybeTaco = tacoRepository.findById(id);
//        if (maybeTaco.isPresent()) {
//            return maybeTaco.get();
//        }
//        return null;
//    }
}
