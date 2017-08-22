package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUsername(String username);
}
