package local;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmartDataRepository extends CrudRepository<SmartData, Long> {

    List<SmartData> findByScreeningId(Long screeningId);

}