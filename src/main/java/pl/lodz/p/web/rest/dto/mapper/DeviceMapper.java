package pl.lodz.p.web.rest.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.lodz.p.domain.entities.Device;
import pl.lodz.p.repository.DeviceRepository;
import pl.lodz.p.web.rest.dto.DeviceDto;

@Component
public class DeviceMapper {

	@Autowired
	private DeviceRepository repository;

	public Device map(DeviceDto device) {
		Device result = null;
		if (device.getId() != null) {
			result = repository.findOne(device.getId());
		} else {
			result = new Device();
		}
		result.setName(device.getName());
		result.setDescription(device.getDescription());
		return result;
	}

	public DeviceDto map(Device device) {
		DeviceDto result = new DeviceDto();
		result.setId(device.getId());
		result.setName(device.getName());
		result.setDescription(device.getDescription());
		return result;
	}
}
