package pl.lodz.p.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import pl.lodz.p.domain.entities.Network;
import pl.lodz.p.repository.NetworkRepository;
import pl.lodz.p.web.rest.util.HeaderUtil;
import pl.lodz.p.web.rest.util.PaginationUtil;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Network.
 */
@RestController
@RequestMapping("/api")
public class NetworkResource {

	private final Logger log = LoggerFactory.getLogger(NetworkResource.class);

	@Inject
	private NetworkRepository repository;

	/**
	 * POST /networks -> Create a new network.
	 */
	@RequestMapping(value = "/networks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Network> createNetwork(@RequestBody Network network) throws URISyntaxException {
		log.debug("REST request to save Network : {}", network);
		if (network.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new network cannot already have an ID").body(null);
		}
		Network result = repository.save(network);
		return ResponseEntity.created(new URI("/api/networks/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("network", result.getId().toString())).body(result);
	}

	/**
	 * PUT /networks -> Updates an existing network.
	 */
	@RequestMapping(value = "/networks", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Network> updateNetwork(@RequestBody Network network) throws URISyntaxException {
		log.debug("REST request to update Network : {}", network);
		if (network.getId() == null) {
			return createNetwork(network);
		}
		Network result = repository.save(network);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("network", network.getId().toString()))
				.body(result);
	}

	/**
	 * GET /networks -> get all the networks.
	 */
	@RequestMapping(value = "/networks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<Network>> getAllNetworks(Pageable pageable) throws URISyntaxException {
		Page<Network> page = repository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/networks");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /networks/:id -> get the "id" network.
	 */
	@RequestMapping(value = "/networks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Network> getNetwork(@PathVariable Long id) {
		log.debug("REST request to get Network : {}", id);
		return Optional.ofNullable(repository.findOne(id)).map(network -> new ResponseEntity<>(network, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /networks/:id -> delete the "id" network.
	 */
	@RequestMapping(value = "/networks/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteNetwork(@PathVariable Long id) {
		log.debug("REST request to delete Network : {}", id);
		repository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("network", id.toString())).build();
	}
}
