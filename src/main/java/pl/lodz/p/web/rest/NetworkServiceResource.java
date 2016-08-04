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

import pl.lodz.p.domain.entities.NetworkService;
import pl.lodz.p.repository.NetworkServiceRepository;
import pl.lodz.p.web.rest.util.HeaderUtil;
import pl.lodz.p.web.rest.util.PaginationUtil;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing NetworkService.
 */
@RestController
@RequestMapping("/api")
public class NetworkServiceResource {

	private final Logger log = LoggerFactory.getLogger(NetworkServiceResource.class);

	@Inject
	private NetworkServiceRepository repository;

	/**
	 * POST /networkServices -> Create a new networkService.
	 */
	@RequestMapping(value = "/networkServices", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<NetworkService> createNetworkService(@RequestBody NetworkService networkService)
			throws URISyntaxException {
		log.debug("REST request to save NetworkService : {}", networkService);
		if (networkService.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new networkService cannot already have an ID")
					.body(null);
		}
		NetworkService result = repository.save(networkService);
		return ResponseEntity.created(new URI("/api/networkServices/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("networkService", result.getId().toString()))
				.body(result);
	}

	/**
	 * PUT /networkServices -> Updates an existing networkService.
	 */
	@RequestMapping(value = "/networkServices", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<NetworkService> updateNetworkService(@RequestBody NetworkService networkService)
			throws URISyntaxException {
		log.debug("REST request to update NetworkService : {}", networkService);
		if (networkService.getId() == null) {
			return createNetworkService(networkService);
		}
		NetworkService result = repository.save(networkService);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("networkService", networkService.getId().toString()))
				.body(result);
	}

	/**
	 * GET /networkServices -> get all the networkServices.
	 */
	@RequestMapping(value = "/networkServices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<NetworkService>> getAllNetworkServices(Pageable pageable) throws URISyntaxException {
		Page<NetworkService> page = repository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/networkServices");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /networkServices/:id -> get the "id" networkService.
	 */
	@RequestMapping(value = "/networkServices/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<NetworkService> getNetworkService(@PathVariable Long id) {
		log.debug("REST request to get NetworkService : {}", id);
		return Optional.ofNullable(repository.findOne(id))
				.map(networkService -> new ResponseEntity<>(networkService, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /networkServices/:id -> delete the "id" networkService.
	 */
	@RequestMapping(value = "/networkServices/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteNetworkService(@PathVariable Long id) {
		log.debug("REST request to delete NetworkService : {}", id);
		repository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("networkService", id.toString()))
				.build();
	}
}
