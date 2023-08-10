package dk.reshr.resource.api.xternal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.reshr.resource.api.model.Resource;
import dk.reshr.resource.api.repository.ResourceDao;
import dk.reshr.resource.api.repository.ResourceId;
import dk.reshr.resource.api.repository.ResourceRepository;
import dk.reshr.resource.api.sevice.ResourceDataService;


@Service
public class ResourceDataServiceImpl implements ResourceDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceDataServiceImpl.class);

	@Autowired
	private ResourceRepository ResourceRepository;

	@Override
	public Resource findById(ResourceId ResourceId) {
		LOGGER.debug("findById {}", ResourceId);
		ResourceDao dao = ResourceRepository.findById(ResourceId).get();
		return toResource(dao);
	}

	@Override
	public Resource saveResource(Resource Resource) {
		ResourceDao d = new ResourceDao();
		d.setId(Resource.getId());
		d.setName(Resource.getName());
		d.setAccountId(Resource.getAccountId());
		ResourceRepository.save(d);
		return Resource;
	}

	@Override
	public List<Resource> fetchResourceListByAccountId(Integer accountId) {
		LOGGER.debug("fetchResource {}");
		List<ResourceDao> Resources = new ArrayList<ResourceDao>();
        Iterable<ResourceDao> iter = ResourceRepository.findAllByAccountId(accountId);
        iter.forEach((p) -> {
    		LOGGER.info("fetchResourceList() --");

            Resources.add(p);
        });
		return toResources(Resources);
	}

	@Override
	public Resource updateResource(Resource Resource, ResourceId resourceId) {
		ResourceDao d = ResourceRepository.findById(resourceId).get();
		d.setName(Resource.getName());

		return Resource;
	}

	@Override
	public void deleteResourceById(ResourceId resourceId) {
		ResourceRepository.deleteById(resourceId);
	}

	private List<Resource> toResources(List<ResourceDao> Resources) {
		LOGGER.info("toResources {}", Resources.size());
		List<Resource> rtn = Resources.stream().map(p -> toResource(p)).collect(Collectors.toList());
		return rtn;
	}

	private Resource toResource(ResourceDao id) {
		LOGGER.info("toResource {}", id.getId());
		Resource d = new Resource();
		d.setId(id.getId());
		d.setName(id.getName());
		d.setAccountId(id.getAccountId());
		return d;
	}

}
