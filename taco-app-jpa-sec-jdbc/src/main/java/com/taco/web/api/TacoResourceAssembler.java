package com.taco.web.api;

import com.taco.domain.Taco;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    protected TacoResource instantiateResource(final Taco taco) {
        return new TacoResource(taco);
    }

    @Override
    public TacoResource toResource(final Taco taco) {
        return createResourceWithId(taco.getId(), taco);
    }

}
