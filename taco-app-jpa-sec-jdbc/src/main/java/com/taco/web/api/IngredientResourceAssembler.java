package com.taco.web.api;

import com.taco.domain.Ingredient;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(Ingredient.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toResource(final Ingredient ingredient) {
        return createResourceWithId(ingredient.getId(), ingredient);
    }

    @Override
    protected IngredientResource instantiateResource(final Ingredient ingredient) {
        return new IngredientResource(ingredient);
    }
}
