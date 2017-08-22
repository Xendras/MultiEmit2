
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Competitor;

public interface CompetitorRepository extends JpaRepository<Competitor, Long> {
    List<Competitor> findByName(String name);
    Competitor findByEmit(String emit);
    
}
