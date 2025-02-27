package tableorder.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import tableorder.domain.*;

@Component
public class TableOrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<TableOrder>> {

    @Override
    public EntityModel<TableOrder> process(EntityModel<TableOrder> model) {
        return model;
    }
}
