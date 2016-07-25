package pl.lodz.p.web.rest.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.lodz.p.domain.entities.NetworkService;
import pl.lodz.p.repository.NetworkServiceRepository;
import pl.lodz.p.web.rest.dto.NetworkServiceDto;

@Component
public class NetworkServiceMapper {

	@Autowired
	private NetworkServiceRepository repository;

	public NetworkService map(NetworkServiceDto service) {
		NetworkService result = null;
		if (service.getId() != null) {
			result = repository.findOne(service.getId());
		} else {
			result = new NetworkService();
		}
		result.setName(service.getName());
		result.setDescription(service.getDescription());
		result.setNetworkInterface(service.getNetworkInterface());
		result.setPort(service.getPort());
		result.setTestScripts(service.getTestScripts());
		return result;
	}

	public NetworkServiceDto map(NetworkService service) {
		NetworkServiceDto result = new NetworkServiceDto();
		result.setId(service.getId());
		result.setName(service.getName());
		result.setDescription(service.getDescription());
		result.setNetworkInterface(service.getNetworkInterface());
		result.setPort(service.getPort());
		result.setTestScripts(service.getTestScripts());
		return result;
	}

}
