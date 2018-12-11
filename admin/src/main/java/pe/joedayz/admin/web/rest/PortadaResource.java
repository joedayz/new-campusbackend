package pe.joedayz.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import pe.joedayz.admin.domain.Portada;
import pe.joedayz.admin.repository.PortadaRepository;
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
 * REST controller for managing Portada.
 */
@RestController
@RequestMapping("/api")
public class PortadaResource {

    private final Logger log = LoggerFactory.getLogger(PortadaResource.class);

    private static final String ENTITY_NAME = "portada";

    private final PortadaRepository portadaRepository;

    public PortadaResource(PortadaRepository portadaRepository) {
        this.portadaRepository = portadaRepository;
    }

    /**
     * POST  /portadas : Create a new portada.
     *
     * @param portada the portada to create
     * @return the ResponseEntity with status 201 (Created) and with body the new portada, or with status 400 (Bad Request) if the portada has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/portadas")
    @Timed
    public ResponseEntity<Portada> createPortada(@Valid @RequestBody Portada portada) throws URISyntaxException {
        log.debug("REST request to save Portada : {}", portada);
        if (portada.getId() != null) {
            throw new BadRequestAlertException("A new portada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Portada result = portadaRepository.save(portada);
        return ResponseEntity.created(new URI("/api/portadas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /portadas : Updates an existing portada.
     *
     * @param portada the portada to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated portada,
     * or with status 400 (Bad Request) if the portada is not valid,
     * or with status 500 (Internal Server Error) if the portada couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/portadas")
    @Timed
    public ResponseEntity<Portada> updatePortada(@Valid @RequestBody Portada portada) throws URISyntaxException {
        log.debug("REST request to update Portada : {}", portada);
        if (portada.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Portada result = portadaRepository.save(portada);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, portada.getId().toString()))
            .body(result);
    }

    /**
     * GET  /portadas : get all the portadas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of portadas in body
     */
    @GetMapping("/portadas")
    @Timed
    public List<Portada> getAllPortadas() {
        log.debug("REST request to get all Portadas");
        return portadaRepository.findAll();
    }

    /**
     * GET  /portadas/:id : get the "id" portada.
     *
     * @param id the id of the portada to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the portada, or with status 404 (Not Found)
     */
    @GetMapping("/portadas/{id}")
    @Timed
    public ResponseEntity<Portada> getPortada(@PathVariable Long id) {
        log.debug("REST request to get Portada : {}", id);
        Optional<Portada> portada = portadaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(portada);
    }

    /**
     * DELETE  /portadas/:id : delete the "id" portada.
     *
     * @param id the id of the portada to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/portadas/{id}")
    @Timed
    public ResponseEntity<Void> deletePortada(@PathVariable Long id) {
        log.debug("REST request to delete Portada : {}", id);

        portadaRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
