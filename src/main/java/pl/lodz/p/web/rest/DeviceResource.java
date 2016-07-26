package pl.lodz.p.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.domain.entities.Device;
import pl.lodz.p.repository.DeviceRepository;
import pl.lodz.p.web.rest.dto.DeviceDto;
import pl.lodz.p.web.rest.dto.mapper.DeviceMapper;
import pl.lodz.p.web.rest.util.HeaderUtil;
import pl.lodz.p.web.rest.util.PaginationUtil;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Device.
 */
@RestController
@RequestMapping("/api")
public class DeviceResource {

	private final Logger log = LoggerFactory.getLogger(DeviceResource.class);

	@Inject
	private DeviceRepository deviceRepository;

	@Autowired
	private DeviceMapper mapper;

	/**
	 * POST /devices -> Create a new device.
	 */
	@RequestMapping(value = "/devices", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Device> createDevice(@RequestBody DeviceDto device) throws URISyntaxException {
		log.debug("REST request to save Device : {}", device);
		if (device.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new device cannot already have an ID").body(null);
		}
		Device result = deviceRepository.save(mapper.map(device));
		return ResponseEntity.created(new URI("/api/devices/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("device", result.getId().toString())).body(result);
	}

	/**
	 * PUT /devices -> Updates an existing device.
	 */
	@RequestMapping(value = "/devices", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Device> updateDevice(@RequestBody Device device) throws URISyntaxException {
		log.debug("REST request to update Device : {}", device);
		if (device.getId() == null) {
			return createDevice(mapper.map(device));
		}
		Device result = deviceRepository.save(device);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("device", device.getId().toString()))
				.body(result);
	}

	/**
	 * GET /devices -> get all the devices.
	 */
	@RequestMapping(value = "/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<Device>> getAllDevices(Pageable pageable) throws URISyntaxException {
		Page<Device> page = deviceRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/devices");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /devices/:id -> get the "id" device.
	 */
	@RequestMapping(value = "/devices/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Device> getDevice(@PathVariable Long id) {
		log.debug("REST request to get Device : {}", id);
		return Optional.ofNullable(deviceRepository.findOne(id))
				.map(device -> new ResponseEntity<>(device, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /devices/:id -> delete the "id" device.
	 */
	@RequestMapping(value = "/devices/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
		log.debug("REST request to delete Device : {}", id);
		deviceRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("device", id.toString())).build();
	}
}
