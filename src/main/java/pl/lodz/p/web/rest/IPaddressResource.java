package pl.lodz.p.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.domain.entities.IPaddress;
import pl.lodz.p.repository.IPaddressRepository;
import pl.lodz.p.web.rest.util.HeaderUtil;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing IPaddress.
 */
@RestController
@RequestMapping("/api")
public class IPaddressResource {

	private final Logger log = LoggerFactory.getLogger(IPaddressResource.class);

	@Inject
	private IPaddressRepository repository;

	/**
	 * POST /ipaddresss -> Create a new ipaddress.
	 */
	@RequestMapping(value = "/ipaddresss", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<IPaddress> createIPaddress(@RequestBody IPaddress ipaddress) throws URISyntaxException {
		log.debug("REST request to save IPaddress : {}", ipaddress);
		if (ipaddress.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new ipaddress cannot already have an ID")
					.body(null);
		}
		IPaddress result = repository.save(ipaddress);
		return ResponseEntity.created(new URI("/api/ipaddresss/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("ipaddress", result.getId().toString())).body(result);
	}

	/**
	 * PUT /ipaddresss -> Updates an existing ipaddress.
	 */
	@RequestMapping(value = "/ipaddresss", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<IPaddress> updateIPaddress(@RequestBody IPaddress ipaddress) throws URISyntaxException {
		log.debug("REST request to update IPaddress : {}", ipaddress);
		if (ipaddress.getId() == null) {
			return createIPaddress(ipaddress);
		}
		IPaddress result = repository.save(ipaddress);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("ipaddress", ipaddress.getId().toString())).body(result);
	}

	/**
	 * GET /ipaddresss -> get all the ipaddresss.
	 */
	@RequestMapping(value = "/ipaddresss", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public List<IPaddress> getAllIPaddresss() {
		log.debug("REST request to get all IPaddresss");
		return repository.findAll();
	}

	/**
	 * GET /ipaddresss/:id -> get the "id" ipaddress.
	 */
	@RequestMapping(value = "/ipaddresss/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<IPaddress> getIPaddress(@PathVariable Long id) {
		log.debug("REST request to get IPaddress : {}", id);
		return Optional.ofNullable(repository.findOne(id))
				.map(ipaddress -> new ResponseEntity<>(ipaddress, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /ipaddresss/:id -> delete the "id" ipaddress.
	 */
	@RequestMapping(value = "/ipaddresss/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteIPaddress(@PathVariable Long id) {
		log.debug("REST request to delete IPaddress : {}", id);
		repository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("ipaddress", id.toString())).build();
	}
}
