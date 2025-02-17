package tableorder.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tableorder.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "salesDataAnalyses",
    path = "salesDataAnalyses"
)
public interface SalesDataAnalysisRepository
    extends PagingAndSortingRepository<SalesDataAnalysis, Long> {}
