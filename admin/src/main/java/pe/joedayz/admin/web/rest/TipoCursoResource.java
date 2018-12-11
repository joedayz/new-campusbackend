package pe.joedayz.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import pe.joedayz.admin.domain.TipoCurso;
import pe.joedayz.admin.repository.TipoCursoRepository;
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
 * REST controller for managing TipoCurso.
 */
@RestController
@RequestMapping("/api")
public class TipoCursoResource {

    private final Logger log = LoggerFactory.getLogger(TipoCursoResource.class);

    private static final String ENTITY_NAME = "tipoCurso";

    private final TipoCursoRepository tipoCursoRepository;

    public TipoCursoResource(TipoCursoRepository tipoCursoRepository) {
        this.tipoCursoRepository = tipoCursoRepository;
    }

    /**
     * POST  /tipo-cursos : Create a new tipoCurso.
     *
     * @param tipoCurso the tipoCurso to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoCurso, or with status 400 (Bad Request) if the tipoCurso has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-cursos")
    @Timed
    public ResponseEntity<TipoCurso> createTipoCurso(@Valid @RequestBody TipoCurso tipoCurso) throws URISyntaxException {
        log.debug("REST request to save TipoCurso : {}", tipoCurso);
        if (tipoCurso.getId() != null) {
            throw new BadRequestAlertException("A new tipoCurso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoCurso result = tipoCursoRepository.save(tipoCurso);
        return ResponseEntity.created(new URI("/api/tipo-cursos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-cursos : Updates an existing tipoCurso.
     *
     * @param tipoCurso the tipoCurso to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoCurso,
     * or with status 400 (Bad Request) if the tipoCurso is not valid,
     * or with status 500 (Internal Server Error) if the tipoCurso couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-cursos")
    @Timed
    public ResponseEntity<TipoCurso> updateTipoCurso(@Valid @RequestBody TipoCurso tipoCurso) throws URISyntaxException {
        log.debug("REST request to update TipoCurso : {}", tipoCurso);
        if (tipoCurso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoCurso result = tipoCursoRepository.save(tipoCurso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoCurso.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-cursos : get all the tipoCursos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoCursos in body
     */
    @GetMapping("/tipo-cursos")
    @Timed
    public List<TipoCurso> getAllTipoCursos() {
        log.debug("REST request to get all TipoCursos");
        return tipoCursoRepository.findAll();
    }

    /**
     * GET  /tipo-cursos/:id : get the "id" tipoCurso.
     *
     * @param id the id of the tipoCurso to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoCurso, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-cursos/{id}")
    @Timed
    public ResponseEntity<TipoCurso> getTipoCurso(@PathVariable Long id) {
        log.debug("REST request to get TipoCurso : {}", id);
        Optional<TipoCurso> tipoCurso = tipoCursoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tipoCurso);
    }

    /**
     * DELETE  /tipo-cursos/:id : delete the "id" tipoCurso.
     *
     * @param id the id of the tipoCurso to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-cursos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoCurso(@PathVariable Long id) {
        log.debug("REST request to delete TipoCurso : {}", id);

        tipoCursoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
