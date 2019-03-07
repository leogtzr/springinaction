package com.taco.data;

import com.taco.domain.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
    // Resources<Taco> findAllPage(PageRequest page);
}
