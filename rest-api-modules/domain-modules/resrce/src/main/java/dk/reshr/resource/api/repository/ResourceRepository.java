package dk.reshr.resource.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository 
extends JpaRepository<ResourceDao, Integer> {}
