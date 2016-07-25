package pl.lodz.p.web.rest.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.lodz.p.domain.entities.VLAN;
import pl.lodz.p.repository.VLANRepository;
import pl.lodz.p.web.rest.dto.VLANDto;

@Component
public class VLANMapper {

	@Autowired
	private VLANRepository repository;

	public VLAN map(VLANDto vlan) {
		VLAN result = null;
		if (vlan.getId() != null) {
			result = repository.findOne(vlan.getId());
		} else {
			result = new VLAN();
		}
		result.setNetworkInterfaces(vlan.getNetworkInterfaces());
		result.setNetworks(vlan.getNetworks());
		result.setNumber(vlan.getNumber());
		result.setTestScripts(vlan.getTestScripts());
		result.setType(vlan.getType());
		result.setVlans(vlan.getVlans());
		return result;
	}

	public VLANDto map(VLAN vlan) {
		VLANDto result = new VLANDto();
		result.setId(vlan.getId());
		result.setNetworkInterfaces(vlan.getNetworkInterfaces());
		result.setNetworks(vlan.getNetworks());
		result.setNumber(vlan.getNumber());
		result.setTestScripts(vlan.getTestScripts());
		result.setType(vlan.getType());
		result.setVlans(vlan.getVlans());
		return result;
	}
}
