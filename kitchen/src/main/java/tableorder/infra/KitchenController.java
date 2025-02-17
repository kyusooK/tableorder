package tableorder.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tableorder.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/kitchens")
@Transactional
public class KitchenController {

    @Autowired
    KitchenRepository kitchenRepository;

    @RequestMapping(
        value = "/kitchens/{id}/cook",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Kitchen cook(
        @PathVariable(value = "id") Long id,
        @RequestBody CookCommand cookCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /kitchen/cook  called #####");
        Optional<Kitchen> optionalKitchen = kitchenRepository.findById(id);

        optionalKitchen.orElseThrow(() -> new Exception("No Entity Found"));
        Kitchen kitchen = optionalKitchen.get();
        kitchen.cook(cookCommand);

        kitchenRepository.save(kitchen);
        return kitchen;
    }

    @RequestMapping(
        value = "/kitchens/{id}/serve",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Kitchen serve(
        @PathVariable(value = "id") Long id,
        @RequestBody ServeCommand serveCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /kitchen/serve  called #####");
        Optional<Kitchen> optionalKitchen = kitchenRepository.findById(id);

        optionalKitchen.orElseThrow(() -> new Exception("No Entity Found"));
        Kitchen kitchen = optionalKitchen.get();
        kitchen.serve(serveCommand);

        kitchenRepository.save(kitchen);
        return kitchen;
    }
}
//>>> Clean Arch / Inbound Adaptor
