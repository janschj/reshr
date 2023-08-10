package dk.reshr.resource.api.sevice;

import java.util.List;

import dk.reshr.resource.api.model.Resource;
import dk.reshr.resource.api.repository.ResourceId;



public interface ResourceDataService {

	// find operation
	Resource findById(ResourceId id);

	// Save operation
	Resource saveResource(Resource Resource);

	// Read operation
	List<Resource> fetchResourceListByAccountId(Integer accountId);

	// Update operation
	Resource updateResource(Resource Resource, ResourceId id);

	// Delete operation
	void deleteResourceById(ResourceId id);

}
