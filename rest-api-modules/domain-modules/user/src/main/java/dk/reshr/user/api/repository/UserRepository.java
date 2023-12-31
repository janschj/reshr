package dk.reshr.user.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository 
extends JpaRepository<UserDao, Integer> {}
