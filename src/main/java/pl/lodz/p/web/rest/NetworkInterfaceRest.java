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

import pl.lodz.p.domain.entities.NetworkInterface;
import pl.lodz.p.repository.NetworkInterfaceRepository;
import pl.lodz.p.web.rest.dto.NetworkInterfaceDto;
import pl.lodz.p.web.rest.dto.mapper.NetworkInterfaceMapper;

@RestController
@RequestMapping("/api")
public class NetworkInterfaceRest {

	@Autowired
	private NetworkInterfaceMapper mapper;

	@Autowired
	private NetworkInterfaceRepository repository;

	@RequestMapping(value = "/interfaces", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(readOnly = true)
	public ResponseEntity<List<NetworkInterface>> getAll() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/interfaces", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NetworkInterface> save(NetworkInterfaceDto networkInterface) {
		return new ResponseEntity<NetworkInterface>(repository.save(mapper.map(networkInterface)), HttpStatus.CREATED);
	}

}
