package com.taco.web.api;

import com.taco.domain.Ingredient;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

public class IngredientResource extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private Ingredient.Type type;

    public IngredientResource(final Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
