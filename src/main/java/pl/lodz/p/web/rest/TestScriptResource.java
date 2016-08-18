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

import pl.lodz.p.domain.entities.DynamicJob;
import pl.lodz.p.domain.entities.TestScript;
import pl.lodz.p.repository.TestScriptRepository;
import pl.lodz.p.service.DynamicJobService;
import pl.lodz.p.web.rest.util.HeaderUtil;
import pl.lodz.p.web.rest.util.PaginationUtil;

import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing TestScript.
 */
@RestController
@RequestMapping("/api")
public class TestScriptResource {

	private final Logger log = LoggerFactory.getLogger(TestScriptResource.class);

	@Inject
	private TestScriptRepository repository;

	@Autowired
	private DynamicJobService service;

	/**
	 * POST /testScripts -> Create a new testScripts.
	 */
	@RequestMapping(value = "/testScripts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<TestScript> createTestScript(@RequestBody TestScript testScripts) throws URISyntaxException {
		log.debug("REST request to save TestScript : {}", testScripts);
		if (testScripts.getId() != null) {
			return ResponseEntity.badRequest().header("Failure", "A new testScripts cannot already have an ID")
					.body(null);
		}
		if (testScripts.getParameters() != null) {
			testScripts.getParameters().forEach(param -> {
				if (param.getTestScript() == null) {
					param.setTestScript(testScripts);
				}
			});
		}
		TestScript result = repository.save(testScripts);
		service.schedule(new DynamicJob(testScripts));
		return ResponseEntity.created(new URI("/api/testScripts/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("testScripts", result.getId().toString())).body(result);
	}

	/**
	 * PUT /testScripts -> Updates an existing testScripts.
	 */
	@RequestMapping(value = "/testScripts", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<TestScript> updateTestScript(@RequestBody TestScript testScripts) throws URISyntaxException {
		log.debug("REST request to update TestScript : {}", testScripts);
		if (testScripts.getId() == null) {
			return createTestScript(testScripts);
		}
		if (testScripts.getParameters() != null) {
			testScripts.getParameters().forEach(param -> {
				if (param.getTestScript() == null) {
					param.setTestScript(testScripts);
				}
			});
		}
		TestScript result = repository.save(testScripts);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert("testScripts", testScripts.getId().toString()))
				.body(result);
	}

	/**
	 * GET /testScripts -> get all the testScripts.
	 */
	@RequestMapping(value = "/testScripts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<TestScript>> getAllTestScripts(Pageable pageable) throws URISyntaxException {
		Page<TestScript> page = repository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/testScripts");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /testScripts/:id -> get the "id" testScripts.
	 */
	@RequestMapping(value = "/testScripts/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<TestScript> getTestScript(@PathVariable Long id) {
		log.debug("REST request to get TestScript : {}", id);
		return Optional.ofNullable(repository.findOne(id))
				.map(testScripts -> new ResponseEntity<>(testScripts, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /testScripts/:id -> delete the "id" testScripts.
	 */
	@RequestMapping(value = "/testScripts/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteTestScript(@PathVariable Long id) {
		log.debug("REST request to delete TestScript : {}", id);
		repository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("testScripts", id.toString())).build();
	}
}
