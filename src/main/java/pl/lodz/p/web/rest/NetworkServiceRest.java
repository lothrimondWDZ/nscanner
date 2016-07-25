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

import pl.lodz.p.domain.entities.NetworkService;
import pl.lodz.p.repository.NetworkServiceRepository;
import pl.lodz.p.web.rest.dto.NetworkServiceDto;
import pl.lodz.p.web.rest.dto.mapper.NetworkServiceMapper;

@RestController
@RequestMapping("/api")
public class NetworkServiceRest {

	@Autowired
	private NetworkServiceMapper mapper;

	@Autowired
	private NetworkServiceRepository repository;

	@RequestMapping(value = "/services", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(readOnly = true)
	public ResponseEntity<List<NetworkService>> getAll() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/services", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NetworkService> save(NetworkServiceDto service) {
		return new ResponseEntity<NetworkService>(repository.save(mapper.map(service)), HttpStatus.CREATED);
	}

}
