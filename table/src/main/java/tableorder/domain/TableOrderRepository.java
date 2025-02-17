package tableorder.domain;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tableorder.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "tableOrders",
    path = "tableOrders"
)
public interface TableOrderRepository
    extends PagingAndSortingRepository<TableOrder, Long> {}
