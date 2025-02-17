package tableorder.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tableorder.domain.*;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "kitchens", path = "kitchens")
public interface KitchenRepository
    extends PagingAndSortingRepository<Kitchen, Long> {}
