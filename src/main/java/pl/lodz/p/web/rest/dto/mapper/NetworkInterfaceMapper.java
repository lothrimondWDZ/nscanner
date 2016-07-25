package pl.lodz.p.web.rest.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.lodz.p.domain.entities.NetworkInterface;
import pl.lodz.p.repository.NetworkInterfaceRepository;
import pl.lodz.p.web.rest.dto.NetworkInterfaceDto;

@Component
public class NetworkInterfaceMapper {

	@Autowired
	private NetworkInterfaceRepository repository;

	public NetworkInterfaceDto map(NetworkInterface networkInterface) {
		NetworkInterfaceDto result = new NetworkInterfaceDto();
		result.setId(networkInterface.getId());
		result.setName(networkInterface.getName());
		result.setDescription(networkInterface.getDescription());
		result.setAddresses(networkInterface.getAddresses());
		result.setType(networkInterface.getType());
		result.setNetworkServices(networkInterface.getNetworkServices());
		result.setVlans(networkInterface.getVlans());
		return result;
	}

	public NetworkInterface map(NetworkInterfaceDto networkInterface) {
		NetworkInterface result = null;
		if (networkInterface.getId() != null) {
			result = repository.findOne(networkInterface.getId());
		} else {
			result = new NetworkInterface();
		}
		result.setName(networkInterface.getName());
		result.setDescription(networkInterface.getDescription());
		result.setAddresses(networkInterface.getAddresses());
		result.setType(networkInterface.getType());
		result.setNetworkServices(networkInterface.getNetworkServices());
		result.setVlans(networkInterface.getVlans());
		return result;
	}
}
