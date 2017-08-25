
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Emit;

public interface EmitRepository extends JpaRepository<Emit, Long> {
    Emit findByOwner(String owner);
    Emit findByNumber(String number);
    
}
