package com.jfn.treasure.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jfn.treasure.service.HuntService;
import com.jfn.treasure.web.rest.errors.BadRequestAlertException;
import com.jfn.treasure.web.rest.util.HeaderUtil;
import com.jfn.treasure.service.dto.HuntDTO;
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
 * REST controller for managing Hunt.
 */
@RestController
@RequestMapping("/api")
public class HuntResource {

    private final Logger log = LoggerFactory.getLogger(HuntResource.class);

    private static final String ENTITY_NAME = "hunt";

    private final HuntService huntService;

    public HuntResource(HuntService huntService) {
        this.huntService = huntService;
    }

    /**
     * POST  /hunts : Create a new hunt.
     *
     * @param huntDTO the huntDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new huntDTO, or with status 400 (Bad Request) if the hunt has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hunts")
    @Timed
    public ResponseEntity<HuntDTO> createHunt(@Valid @RequestBody HuntDTO huntDTO) throws URISyntaxException {
        log.debug("REST request to save Hunt : {}", huntDTO);
        if (huntDTO.getId() != null) {
            throw new BadRequestAlertException("A new hunt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HuntDTO result = huntService.save(huntDTO);
        return ResponseEntity.created(new URI("/api/hunts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hunts : Updates an existing hunt.
     *
     * @param huntDTO the huntDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated huntDTO,
     * or with status 400 (Bad Request) if the huntDTO is not valid,
     * or with status 500 (Internal Server Error) if the huntDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hunts")
    @Timed
    public ResponseEntity<HuntDTO> updateHunt(@Valid @RequestBody HuntDTO huntDTO) throws URISyntaxException {
        log.debug("REST request to update Hunt : {}", huntDTO);
        if (huntDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HuntDTO result = huntService.save(huntDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, huntDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hunts : get all the hunts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hunts in body
     */
    @GetMapping("/hunts")
    @Timed
    public List<HuntDTO> getAllHunts() {
        log.debug("REST request to get all Hunts");
        return huntService.findAll();
    }

    /**
     * GET  /hunts/:id : get the "id" hunt.
     *
     * @param id the id of the huntDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the huntDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hunts/{id}")
    @Timed
    public ResponseEntity<HuntDTO> getHunt(@PathVariable String id) {
        log.debug("REST request to get Hunt : {}", id);
        Optional<HuntDTO> huntDTO = huntService.findOne(id);
        return ResponseUtil.wrapOrNotFound(huntDTO);
    }

    /**
     * DELETE  /hunts/:id : delete the "id" hunt.
     *
     * @param id the id of the huntDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hunts/{id}")
    @Timed
    public ResponseEntity<Void> deleteHunt(@PathVariable String id) {
        log.debug("REST request to delete Hunt : {}", id);
        huntService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
