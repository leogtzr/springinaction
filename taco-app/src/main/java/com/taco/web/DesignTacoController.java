package com.taco.web;

import com.taco.domain.Ingredient;
import com.taco.domain.Ingredient.Type;
import com.taco.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(final Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );

        final Type[] types = Type.values();
        for (final Type type : types) {
            final Optional<Ingredient> filteredType = filterByType(ingredients, type);
            if (filteredType.isPresent()) {
                model.addAttribute(type.toString().toLowerCase(), filteredType.get());
            }
        }

        model.addAttribute("design", new Taco());

        return "design";            // the logical view "design.html"
    }

    private Optional<Ingredient> filterByType(final List<Ingredient> ingredients, final Type type) {
        return ingredients.stream().filter(t -> t.getType().equals(type)).findAny();
    }

    @PostMapping
    public String processDesign(final Taco design) {
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

}
