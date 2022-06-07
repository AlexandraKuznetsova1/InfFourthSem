package ru.itis.resale.javalabresale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.resale.javalabresale.models.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String login);
}
