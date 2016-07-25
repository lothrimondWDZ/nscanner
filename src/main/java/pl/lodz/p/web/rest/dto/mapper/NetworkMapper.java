package pl.lodz.p.web.rest.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.lodz.p.domain.entities.Network;
import pl.lodz.p.repository.NetworkRepository;
import pl.lodz.p.web.rest.dto.NetworkDto;

@Component
public class NetworkMapper {

	@Autowired
	private NetworkRepository repository;

	public Network map(NetworkDto network) {
		Network result = null;
		if (network.getId() != null) {
			result = repository.findOne(network.getId());
		} else {
			result = new Network();
		}
		result.setName(network.getName());
		result.setBroadcast(network.getBroadcast());
		result.setConnectedDevices(network.getConnectedDevices());
		result.setMask(network.getMask());
		result.setNetworkAddress(network.getNetworkAddress());
		return result;
	}

	public NetworkDto map(Network network) {
		NetworkDto result = new NetworkDto();
		result.setId(network.getId());
		result.setName(network.getName());
		result.setBroadcast(network.getBroadcast());
		result.setConnectedDevices(network.getConnectedDevices());
		result.setMask(network.getMask());
		result.setNetworkAddress(network.getNetworkAddress());
		return result;
	}

}
