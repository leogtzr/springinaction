package com.taco.data;

import com.taco.domain.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.Resources;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    Resources<Taco> findAll(PageRequest page);
}
