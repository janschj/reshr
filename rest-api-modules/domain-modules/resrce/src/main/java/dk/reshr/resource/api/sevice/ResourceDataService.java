package dk.reshr.resource.api.sevice;

import java.util.List;

import dk.reshr.resource.api.model.Resource;



public interface ResourceDataService {

	// find operation
	Resource findById(Integer id);

	// Save operation
	Resource saveResource(Resource Resource);

	// Read operation
	List<Resource> fetchResourceList();

	// Update operation
	Resource updateResource(Resource Resource, Integer id);

	// Delete operation
	void deleteResourceById(Integer id);

}
