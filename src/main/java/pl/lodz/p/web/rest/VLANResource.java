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

import pl.lodz.p.domain.entities.VLAN;
import pl.lodz.p.repository.VLANRepository;
import pl.lodz.p.web.rest.util.HeaderUtil;
import pl.lodz.p.web.rest.util.PaginationUtil;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing VLAN.
 */
@RestController
@RequestMapping("/api")
public class VLANResource {

	private final Logger log = LoggerFactory.getLogger(VLANResource.class);

	@Inject
	private VLANRepository vLANRepository;

	/**
	 * POST /vLANs -> Create a new vLAN.
	 */
	@RequestMapping(value = "/vlans", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<VLAN> createVLAN(@RequestBody VLAN vLAN) throws URISyntaxException {
		log.debug("REST request to save VLAN : {}", vLAN);
		if (vLAN.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new vLAN cannot already have an ID").body(null);
		}
		VLAN result = vLANRepository.save(vLAN);
		return ResponseEntity.created(new URI("/api/vLANs/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("vLAN", result.getId().toString())).body(result);
	}

	/**
	 * PUT /vLANs -> Updates an existing vLAN.
	 */
	@RequestMapping(value = "/vlans", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<VLAN> updateVLAN(@RequestBody VLAN vLAN) throws URISyntaxException {
		log.debug("REST request to update VLAN : {}", vLAN);
		if (vLAN.getId() == null) {
			return createVLAN(vLAN);
		}
		VLAN result = vLANRepository.save(vLAN);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert("vLAN", vLAN.getId().toString()))
				.body(result);
	}

	/**
	 * GET /vLANs -> get all the vLANs.
	 */
	@RequestMapping(value = "/vlans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<VLAN>> getAllVLANs(Pageable pageable) throws URISyntaxException {
		Page<VLAN> page = vLANRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vLANs");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /vLANs/:id -> get the "id" vLAN.
	 */
	@RequestMapping(value = "/vlans/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<VLAN> getVLAN(@PathVariable Long id) {
		log.debug("REST request to get VLAN : {}", id);
		return Optional.ofNullable(vLANRepository.findOne(id)).map(vLAN -> new ResponseEntity<>(vLAN, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /vLANs/:id -> delete the "id" vLAN.
	 */
	@RequestMapping(value = "/vlans/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteVLAN(@PathVariable Long id) {
		log.debug("REST request to delete VLAN : {}", id);
		vLANRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("vLAN", id.toString())).build();
	}
}
