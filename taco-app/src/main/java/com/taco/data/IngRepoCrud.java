package com.taco.data;

import com.taco.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngRepoCrud extends CrudRepository<Ingredient, String> {
}
