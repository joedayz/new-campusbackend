package pe.joedayz.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import pe.joedayz.admin.domain.TemasCurso;
import pe.joedayz.admin.repository.TemasCursoRepository;
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
 * REST controller for managing TemasCurso.
 */
@RestController
@RequestMapping("/api")
public class TemasCursoResource {

    private final Logger log = LoggerFactory.getLogger(TemasCursoResource.class);

    private static final String ENTITY_NAME = "temasCurso";

    private final TemasCursoRepository temasCursoRepository;

    public TemasCursoResource(TemasCursoRepository temasCursoRepository) {
        this.temasCursoRepository = temasCursoRepository;
    }

    /**
     * POST  /temas-cursos : Create a new temasCurso.
     *
     * @param temasCurso the temasCurso to create
     * @return the ResponseEntity with status 201 (Created) and with body the new temasCurso, or with status 400 (Bad Request) if the temasCurso has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/temas-cursos")
    @Timed
    public ResponseEntity<TemasCurso> createTemasCurso(@Valid @RequestBody TemasCurso temasCurso) throws URISyntaxException {
        log.debug("REST request to save TemasCurso : {}", temasCurso);
        if (temasCurso.getId() != null) {
            throw new BadRequestAlertException("A new temasCurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TemasCurso result = temasCursoRepository.save(temasCurso);
        return ResponseEntity.created(new URI("/api/temas-cursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /temas-cursos : Updates an existing temasCurso.
     *
     * @param temasCurso the temasCurso to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated temasCurso,
     * or with status 400 (Bad Request) if the temasCurso is not valid,
     * or with status 500 (Internal Server Error) if the temasCurso couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/temas-cursos")
    @Timed
    public ResponseEntity<TemasCurso> updateTemasCurso(@Valid @RequestBody TemasCurso temasCurso) throws URISyntaxException {
        log.debug("REST request to update TemasCurso : {}", temasCurso);
        if (temasCurso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TemasCurso result = temasCursoRepository.save(temasCurso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, temasCurso.getId().toString()))
            .body(result);
    }

    /**
     * GET  /temas-cursos : get all the temasCursos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of temasCursos in body
     */
    @GetMapping("/temas-cursos")
    @Timed
    public List<TemasCurso> getAllTemasCursos() {
        log.debug("REST request to get all TemasCursos");
        return temasCursoRepository.findAll();
    }

    /**
     * GET  /temas-cursos/:id : get the "id" temasCurso.
     *
     * @param id the id of the temasCurso to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the temasCurso, or with status 404 (Not Found)
     */
    @GetMapping("/temas-cursos/{id}")
    @Timed
    public ResponseEntity<TemasCurso> getTemasCurso(@PathVariable Long id) {
        log.debug("REST request to get TemasCurso : {}", id);
        Optional<TemasCurso> temasCurso = temasCursoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(temasCurso);
    }

    /**
     * DELETE  /temas-cursos/:id : delete the "id" temasCurso.
     *
     * @param id the id of the temasCurso to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/temas-cursos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTemasCurso(@PathVariable Long id) {
        log.debug("REST request to delete TemasCurso : {}", id);

        temasCursoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
