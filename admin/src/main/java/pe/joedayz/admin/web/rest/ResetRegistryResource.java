package pe.joedayz.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import pe.joedayz.admin.domain.ResetRegistry;
import pe.joedayz.admin.repository.ResetRegistryRepository;
import pe.joedayz.admin.web.rest.errors.BadRequestAlertException;
import pe.joedayz.admin.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ResetRegistry.
 */
@RestController
@RequestMapping("/api")
public class ResetRegistryResource {

    private final Logger log = LoggerFactory.getLogger(ResetRegistryResource.class);

    private static final String ENTITY_NAME = "resetRegistry";

    private final ResetRegistryRepository resetRegistryRepository;

    public ResetRegistryResource(ResetRegistryRepository resetRegistryRepository) {
        this.resetRegistryRepository = resetRegistryRepository;
    }

    /**
     * POST  /reset-registries : Create a new resetRegistry.
     *
     * @param resetRegistry the resetRegistry to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resetRegistry, or with status 400 (Bad Request) if the resetRegistry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reset-registries")
    @Timed
    public ResponseEntity<ResetRegistry> createResetRegistry(@Valid @RequestBody ResetRegistry resetRegistry) throws URISyntaxException {
        log.debug("REST request to save ResetRegistry : {}", resetRegistry);
        if (resetRegistry.getId() != null) {
            throw new BadRequestAlertException("A new resetRegistry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResetRegistry result = resetRegistryRepository.save(resetRegistry);
        return ResponseEntity.created(new URI("/api/reset-registries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reset-registries : Updates an existing resetRegistry.
     *
     * @param resetRegistry the resetRegistry to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resetRegistry,
     * or with status 400 (Bad Request) if the resetRegistry is not valid,
     * or with status 500 (Internal Server Error) if the resetRegistry couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reset-registries")
    @Timed
    public ResponseEntity<ResetRegistry> updateResetRegistry(@Valid @RequestBody ResetRegistry resetRegistry) throws URISyntaxException {
        log.debug("REST request to update ResetRegistry : {}", resetRegistry);
        if (resetRegistry.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResetRegistry result = resetRegistryRepository.save(resetRegistry);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resetRegistry.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reset-registries : get all the resetRegistries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of resetRegistries in body
     */
    @GetMapping("/reset-registries")
    @Timed
    public List<ResetRegistry> getAllResetRegistries() {
        log.debug("REST request to get all ResetRegistries");
        return resetRegistryRepository.findAll();
    }

    /**
     * GET  /reset-registries/:id : get the "id" resetRegistry.
     *
     * @param id the id of the resetRegistry to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resetRegistry, or with status 404 (Not Found)
     */
    @GetMapping("/reset-registries/{id}")
    @Timed
    public ResponseEntity<ResetRegistry> getResetRegistry(@PathVariable Long id) {
        log.debug("REST request to get ResetRegistry : {}", id);
        Optional<ResetRegistry> resetRegistry = resetRegistryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resetRegistry);
    }

    /**
     * DELETE  /reset-registries/:id : delete the "id" resetRegistry.
     *
     * @param id the id of the resetRegistry to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reset-registries/{id}")
    @Timed
    public ResponseEntity<Void> deleteResetRegistry(@PathVariable Long id) {
        log.debug("REST request to delete ResetRegistry : {}", id);

        resetRegistryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
