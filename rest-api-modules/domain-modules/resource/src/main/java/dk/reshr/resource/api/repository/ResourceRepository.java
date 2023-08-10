package dk.reshr.resource.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceDao, ResourceId> {

	List<ResourceDao> findAllByAccountId(Integer accountId);

};
