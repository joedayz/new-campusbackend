package pe.joedayz.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import pe.joedayz.admin.domain.DatosCurso;
import pe.joedayz.admin.repository.DatosCursoRepository;
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
 * REST controller for managing DatosCurso.
 */
@RestController
@RequestMapping("/api")
public class DatosCursoResource {

    private final Logger log = LoggerFactory.getLogger(DatosCursoResource.class);

    private static final String ENTITY_NAME = "datosCurso";

    private final DatosCursoRepository datosCursoRepository;

    public DatosCursoResource(DatosCursoRepository datosCursoRepository) {
        this.datosCursoRepository = datosCursoRepository;
    }

    /**
     * POST  /datos-cursos : Create a new datosCurso.
     *
     * @param datosCurso the datosCurso to create
     * @return the ResponseEntity with status 201 (Created) and with body the new datosCurso, or with status 400 (Bad Request) if the datosCurso has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/datos-cursos")
    @Timed
    public ResponseEntity<DatosCurso> createDatosCurso(@Valid @RequestBody DatosCurso datosCurso) throws URISyntaxException {
        log.debug("REST request to save DatosCurso : {}", datosCurso);
        if (datosCurso.getId() != null) {
            throw new BadRequestAlertException("A new datosCurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DatosCurso result = datosCursoRepository.save(datosCurso);
        return ResponseEntity.created(new URI("/api/datos-cursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /datos-cursos : Updates an existing datosCurso.
     *
     * @param datosCurso the datosCurso to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated datosCurso,
     * or with status 400 (Bad Request) if the datosCurso is not valid,
     * or with status 500 (Internal Server Error) if the datosCurso couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/datos-cursos")
    @Timed
    public ResponseEntity<DatosCurso> updateDatosCurso(@Valid @RequestBody DatosCurso datosCurso) throws URISyntaxException {
        log.debug("REST request to update DatosCurso : {}", datosCurso);
        if (datosCurso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DatosCurso result = datosCursoRepository.save(datosCurso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, datosCurso.getId().toString()))
            .body(result);
    }

    /**
     * GET  /datos-cursos : get all the datosCursos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of datosCursos in body
     */
    @GetMapping("/datos-cursos")
    @Timed
    public List<DatosCurso> getAllDatosCursos() {
        log.debug("REST request to get all DatosCursos");
        return datosCursoRepository.findAll();
    }

    /**
     * GET  /datos-cursos/:id : get the "id" datosCurso.
     *
     * @param id the id of the datosCurso to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the datosCurso, or with status 404 (Not Found)
     */
    @GetMapping("/datos-cursos/{id}")
    @Timed
    public ResponseEntity<DatosCurso> getDatosCurso(@PathVariable Long id) {
        log.debug("REST request to get DatosCurso : {}", id);
        Optional<DatosCurso> datosCurso = datosCursoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(datosCurso);
    }

    /**
     * DELETE  /datos-cursos/:id : delete the "id" datosCurso.
     *
     * @param id the id of the datosCurso to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/datos-cursos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDatosCurso(@PathVariable Long id) {
        log.debug("REST request to delete DatosCurso : {}", id);

        datosCursoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
