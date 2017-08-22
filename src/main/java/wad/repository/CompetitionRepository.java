
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findByLocation(String location);
    List<Competition> findByDate(String date);
    List<Competition> findByName(String name);
    
}
