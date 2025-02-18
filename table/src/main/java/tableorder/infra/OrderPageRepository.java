package tableorder.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tableorder.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "orderPages",
    path = "orderPages"
)
public interface OrderPageRepository
    extends PagingAndSortingRepository<OrderPage, Long> {

    List<OrderPage> findByOrderId(Long id);}
