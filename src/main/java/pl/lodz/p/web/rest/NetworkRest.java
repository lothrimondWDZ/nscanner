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

import pl.lodz.p.domain.entities.Network;
import pl.lodz.p.repository.NetworkRepository;
import pl.lodz.p.web.rest.dto.NetworkDto;
import pl.lodz.p.web.rest.dto.mapper.NetworkMapper;

@RestController
@RequestMapping("/api")
public class NetworkRest {

	@Autowired
	private NetworkMapper mapper;

	@Autowired
	private NetworkRepository repository;

	@RequestMapping(value = "/networks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(readOnly = true)
	public ResponseEntity<List<Network>> getAll() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/networks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Network> save(NetworkDto network) {
		return new ResponseEntity<Network>(repository.save(mapper.map(network)), HttpStatus.CREATED);
	}

}
