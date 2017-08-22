
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
    
}
