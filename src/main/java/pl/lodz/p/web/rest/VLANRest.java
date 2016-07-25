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

import pl.lodz.p.domain.entities.VLAN;
import pl.lodz.p.repository.VLANRepository;
import pl.lodz.p.web.rest.dto.VLANDto;
import pl.lodz.p.web.rest.dto.mapper.VLANMapper;

@RestController
@RequestMapping("/api")
public class VLANRest {

	@Autowired
	private VLANMapper mapper;

	@Autowired
	private VLANRepository repository;

	@RequestMapping(value = "/vlans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(readOnly = true)
	public ResponseEntity<List<VLAN>> getAll() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/vlans", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VLAN> save(VLANDto vlan) {
		return new ResponseEntity<VLAN>(repository.save(mapper.map(vlan)), HttpStatus.CREATED);
	}

}
