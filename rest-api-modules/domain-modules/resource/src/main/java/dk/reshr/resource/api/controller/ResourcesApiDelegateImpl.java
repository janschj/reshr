package dk.reshr.resource.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.reshr.resource.api.ResourcesApiDelegate;
import dk.reshr.resource.api.model.Resource;
import dk.reshr.resource.api.repository.ResourceId;
import dk.reshr.resource.api.sevice.ResourceDataService;


@Service
public class ResourcesApiDelegateImpl implements ResourcesApiDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesApiDelegateImpl.class);

	@Autowired
    private ResourceDataService ResourceDataService;

	@Override
	public ResponseEntity<Void> createResource(Integer accountId, Resource Resource) {
		LOGGER.info("createResource id {}", Resource.getId());
		ResourceDataService.saveResource(Resource);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<Resource>> listResourcesByAccountId(Integer accountId) {
		LOGGER.info("listResources {}", accountId);
		List<Resource>Resources = new ArrayList<>();
		Resources.addAll(ResourceDataService.fetchResourceListByAccountId(accountId));
		return new ResponseEntity<>(Resources, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Resource> showResourcesById(Integer accountId, Integer resourceId) {
		LOGGER.info("showResourceById {}", accountId);
		ResourceId r = new ResourceId(accountId, resourceId);
		Resource Resource = ResourceDataService.findById(r);
		return new ResponseEntity<>(Resource, HttpStatus.OK);
	}

}
