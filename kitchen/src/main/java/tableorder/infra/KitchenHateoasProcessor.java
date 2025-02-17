package tableorder.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import tableorder.domain.*;

@Component
public class KitchenHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Kitchen>> {

    @Override
    public EntityModel<Kitchen> process(EntityModel<Kitchen> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/cook")
                .withRel("cook")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/serve")
                .withRel("serve")
        );

        return model;
    }
}
