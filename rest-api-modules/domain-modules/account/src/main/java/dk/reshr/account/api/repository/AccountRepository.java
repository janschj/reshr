package dk.reshr.account.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository 
extends JpaRepository<AccountDao, String> {}
