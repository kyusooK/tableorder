package tableorder.infra;
import tableorder.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/tableOrders")
@Transactional
public class TableOrderController {
    @Autowired
    TableOrderRepository tableOrderRepository;

    @RequestMapping(value = "/tableOrders/주문",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public TableOrder 주문(HttpServletRequest request, HttpServletResponse response, 
        ) throws Exception {
            System.out.println("##### /tableOrder/주문  called #####");
            TableOrder tableOrder = new TableOrder();
            tableOrder.주문();
            tableOrderRepository.save(tableOrder);
            return tableOrder;
    }
}
//>>> Clean Arch / Inbound Adaptor
