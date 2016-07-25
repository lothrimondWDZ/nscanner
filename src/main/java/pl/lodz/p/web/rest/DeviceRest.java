package pl.lodz.p.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.domain.entities.Device;
import pl.lodz.p.repository.DeviceRepository;
import pl.lodz.p.web.rest.dto.DeviceDto;
import pl.lodz.p.web.rest.dto.mapper.DeviceMapper;

@RestController
@RequestMapping("/api")
public class DeviceRest {

	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private DeviceRepository deviceRepository;

	@RequestMapping(value = "/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(readOnly = true)
	public ResponseEntity<List<Device>> getAll() {
		return new ResponseEntity<>(deviceRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Device> save(DeviceDto device) {
		return new ResponseEntity<Device>(deviceRepository.save(deviceMapper.map(device)), HttpStatus.CREATED);
	}
}
