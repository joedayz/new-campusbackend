package pe.joedayz.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import pe.joedayz.admin.domain.GeneralTable;
import pe.joedayz.admin.repository.GeneralTableRepository;
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
 * REST controller for managing GeneralTable.
 */
@RestController
@RequestMapping("/api")
public class GeneralTableResource {

    private final Logger log = LoggerFactory.getLogger(GeneralTableResource.class);

    private static final String ENTITY_NAME = "generalTable";

    private final GeneralTableRepository generalTableRepository;

    public GeneralTableResource(GeneralTableRepository generalTableRepository) {
        this.generalTableRepository = generalTableRepository;
    }

    /**
     * POST  /general-tables : Create a new generalTable.
     *
     * @param generalTable the generalTable to create
     * @return the ResponseEntity with status 201 (Created) and with body the new generalTable, or with status 400 (Bad Request) if the generalTable has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/general-tables")
    @Timed
    public ResponseEntity<GeneralTable> createGeneralTable(@Valid @RequestBody GeneralTable generalTable) throws URISyntaxException {
        log.debug("REST request to save GeneralTable : {}", generalTable);
        if (generalTable.getId() != null) {
            throw new BadRequestAlertException("A new generalTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeneralTable result = generalTableRepository.save(generalTable);
        return ResponseEntity.created(new URI("/api/general-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /general-tables : Updates an existing generalTable.
     *
     * @param generalTable the generalTable to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated generalTable,
     * or with status 400 (Bad Request) if the generalTable is not valid,
     * or with status 500 (Internal Server Error) if the generalTable couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/general-tables")
    @Timed
    public ResponseEntity<GeneralTable> updateGeneralTable(@Valid @RequestBody GeneralTable generalTable) throws URISyntaxException {
        log.debug("REST request to update GeneralTable : {}", generalTable);
        if (generalTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeneralTable result = generalTableRepository.save(generalTable);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, generalTable.getId().toString()))
            .body(result);
    }

    /**
     * GET  /general-tables : get all the generalTables.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of generalTables in body
     */
    @GetMapping("/general-tables")
    @Timed
    public List<GeneralTable> getAllGeneralTables() {
        log.debug("REST request to get all GeneralTables");
        return generalTableRepository.findAll();
    }

    /**
     * GET  /general-tables/:id : get the "id" generalTable.
     *
     * @param id the id of the generalTable to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the generalTable, or with status 404 (Not Found)
     */
    @GetMapping("/general-tables/{id}")
    @Timed
    public ResponseEntity<GeneralTable> getGeneralTable(@PathVariable Long id) {
        log.debug("REST request to get GeneralTable : {}", id);
        Optional<GeneralTable> generalTable = generalTableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(generalTable);
    }

    /**
     * DELETE  /general-tables/:id : delete the "id" generalTable.
     *
     * @param id the id of the generalTable to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/general-tables/{id}")
    @Timed
    public ResponseEntity<Void> deleteGeneralTable(@PathVariable Long id) {
        log.debug("REST request to delete GeneralTable : {}", id);

        generalTableRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
