package pe.joedayz.admin.web.rest;

import com.codahale.metrics.annotation.Timed;
import pe.joedayz.admin.domain.Hangout;
import pe.joedayz.admin.repository.HangoutRepository;
import pe.joedayz.admin.web.rest.errors.BadRequestAlertException;
import pe.joedayz.admin.web.rest.util.HeaderUtil;
import pe.joedayz.admin.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Hangout.
 */
@RestController
@RequestMapping("/api")
public class HangoutResource {

    private final Logger log = LoggerFactory.getLogger(HangoutResource.class);

    private static final String ENTITY_NAME = "hangout";

    private final HangoutRepository hangoutRepository;

    public HangoutResource(HangoutRepository hangoutRepository) {
        this.hangoutRepository = hangoutRepository;
    }

    /**
     * POST  /hangouts : Create a new hangout.
     *
     * @param hangout the hangout to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hangout, or with status 400 (Bad Request) if the hangout has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hangouts")
    @Timed
    public ResponseEntity<Hangout> createHangout(@Valid @RequestBody Hangout hangout) throws URISyntaxException {
        log.debug("REST request to save Hangout : {}", hangout);
        if (hangout.getId() != null) {
            throw new BadRequestAlertException("A new hangout cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Hangout result = hangoutRepository.save(hangout);
        return ResponseEntity.created(new URI("/api/hangouts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hangouts : Updates an existing hangout.
     *
     * @param hangout the hangout to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hangout,
     * or with status 400 (Bad Request) if the hangout is not valid,
     * or with status 500 (Internal Server Error) if the hangout couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hangouts")
    @Timed
    public ResponseEntity<Hangout> updateHangout(@Valid @RequestBody Hangout hangout) throws URISyntaxException {
        log.debug("REST request to update Hangout : {}", hangout);
        if (hangout.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Hangout result = hangoutRepository.save(hangout);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hangout.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hangouts : get all the hangouts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hangouts in body
     */
    @GetMapping("/hangouts")
    @Timed
    public ResponseEntity<List<Hangout>> getAllHangouts(Pageable pageable) {
        log.debug("REST request to get a page of Hangouts");
        Page<Hangout> page = hangoutRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hangouts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /hangouts/:id : get the "id" hangout.
     *
     * @param id the id of the hangout to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hangout, or with status 404 (Not Found)
     */
    @GetMapping("/hangouts/{id}")
    @Timed
    public ResponseEntity<Hangout> getHangout(@PathVariable Long id) {
        log.debug("REST request to get Hangout : {}", id);
        Optional<Hangout> hangout = hangoutRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hangout);
    }

    /**
     * DELETE  /hangouts/:id : delete the "id" hangout.
     *
     * @param id the id of the hangout to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hangouts/{id}")
    @Timed
    public ResponseEntity<Void> deleteHangout(@PathVariable Long id) {
        log.debug("REST request to delete Hangout : {}", id);

        hangoutRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
