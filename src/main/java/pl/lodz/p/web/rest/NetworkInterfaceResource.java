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

import pl.lodz.p.domain.entities.NetworkInterface;
import pl.lodz.p.repository.NetworkInterfaceRepository;
import pl.lodz.p.repository.VLANRepository;
import pl.lodz.p.web.rest.util.HeaderUtil;
import pl.lodz.p.web.rest.util.PaginationUtil;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing NetworkInterface.
 */
@RestController
@RequestMapping("/api")
public class NetworkInterfaceResource {

	private final Logger log = LoggerFactory.getLogger(NetworkInterfaceResource.class);

	@Inject
	private NetworkInterfaceRepository repository;
	@Inject
	private VLANRepository vlanRepository;

	/**
	 * POST /networkInterfaces -> Create a new networkInterface.
	 */
	@RequestMapping(value = "/networkInterfaces", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<NetworkInterface> createNetworkInterface(@RequestBody NetworkInterface networkInterface)
			throws URISyntaxException {
		log.debug("REST request to save NetworkInterface : {}", networkInterface);
		if (networkInterface.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new networkInterface cannot already have an ID")
					.body(null);
		}
		networkInterface.getAddresses().forEach(address -> {
			if (address.getNetworkInterface() == null) {
				address.setNetworkInterface(networkInterface);
			}
		});
		NetworkInterface result = repository.save(networkInterface);
		return ResponseEntity.created(new URI("/api/networkInterfaces/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("networkInterface", result.getId().toString()))
				.body(result);
	}

	/**
	 * PUT /networkInterfaces -> Updates an existing networkInterface.
	 */
	@RequestMapping(value = "/networkInterfaces", method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<NetworkInterface> updateNetworkInterface(@RequestBody NetworkInterface networkInterface)
			throws URISyntaxException {
		log.debug("REST request to update NetworkInterface : {}", networkInterface);
		if (networkInterface.getId() == null) {
			return createNetworkInterface(networkInterface);
		}
		networkInterface.getAddresses().forEach(address -> {
			if (address.getNetworkInterface() == null) {
				address.setNetworkInterface(networkInterface);
			}
		});
		NetworkInterface result = repository.save(networkInterface);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("networkInterface", networkInterface.getId().toString()))
				.body(result);
	}

	/**
	 * GET /networkInterfaces -> get all the networkInterfaces.
	 */
	@RequestMapping(value = "/networkInterfaces", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<NetworkInterface>> getAllNetworkInterfaces(Pageable pageable) throws URISyntaxException {
		Page<NetworkInterface> page = repository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/networkInterfaces");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /networkInterfaces/:id -> get the "id" networkInterface.
	 */
	@RequestMapping(value = "/networkInterfaces/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<NetworkInterface> getNetworkInterface(@PathVariable Long id) {
		log.debug("REST request to get NetworkInterface : {}", id);
		return Optional.ofNullable(repository.findOne(id))
				.map(networkInterface -> new ResponseEntity<>(networkInterface, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /networkInterfaces/:id -> delete the "id" networkInterface.
	 */
	@RequestMapping(value = "/networkInterfaces/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteNetworkInterface(@PathVariable Long id) {
		log.debug("REST request to delete NetworkInterface : {}", id);
		repository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("networkInterface", id.toString()))
				.build();
	}

}
