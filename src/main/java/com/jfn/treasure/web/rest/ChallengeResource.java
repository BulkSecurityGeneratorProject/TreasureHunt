package com.jfn.treasure.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jfn.treasure.service.ChallengeService;
import com.jfn.treasure.web.rest.errors.BadRequestAlertException;
import com.jfn.treasure.web.rest.util.HeaderUtil;
import com.jfn.treasure.web.rest.util.PaginationUtil;
import com.jfn.treasure.service.dto.ChallengeDTO;
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
 * REST controller for managing Challenge.
 */
@RestController
@RequestMapping("/api")
public class ChallengeResource {

    private final Logger log = LoggerFactory.getLogger(ChallengeResource.class);

    private static final String ENTITY_NAME = "challenge";

    private final ChallengeService challengeService;

    public ChallengeResource(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    /**
     * POST  /challenges : Create a new challenge.
     *
     * @param challengeDTO the challengeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new challengeDTO, or with status 400 (Bad Request) if the challenge has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/challenges")
    @Timed
    public ResponseEntity<ChallengeDTO> createChallenge(@Valid @RequestBody ChallengeDTO challengeDTO) throws URISyntaxException {
        log.debug("REST request to save Challenge : {}", challengeDTO);
        if (challengeDTO.getId() != null) {
            throw new BadRequestAlertException("A new challenge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChallengeDTO result = challengeService.save(challengeDTO);
        return ResponseEntity.created(new URI("/api/challenges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /challenges : Updates an existing challenge.
     *
     * @param challengeDTO the challengeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated challengeDTO,
     * or with status 400 (Bad Request) if the challengeDTO is not valid,
     * or with status 500 (Internal Server Error) if the challengeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/challenges")
    @Timed
    public ResponseEntity<ChallengeDTO> updateChallenge(@Valid @RequestBody ChallengeDTO challengeDTO) throws URISyntaxException {
        log.debug("REST request to update Challenge : {}", challengeDTO);
        if (challengeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChallengeDTO result = challengeService.save(challengeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, challengeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /challenges : get all the challenges.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of challenges in body
     */
    @GetMapping("/challenges")
    @Timed
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges(Pageable pageable) {
        log.debug("REST request to get a page of Challenges");
        Page<ChallengeDTO> page = challengeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/challenges");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /challenges/:id : get the "id" challenge.
     *
     * @param id the id of the challengeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the challengeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/challenges/{id}")
    @Timed
    public ResponseEntity<ChallengeDTO> getChallenge(@PathVariable String id) {
        log.debug("REST request to get Challenge : {}", id);
        Optional<ChallengeDTO> challengeDTO = challengeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(challengeDTO);
    }

    /**
     * DELETE  /challenges/:id : delete the "id" challenge.
     *
     * @param id the id of the challengeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/challenges/{id}")
    @Timed
    public ResponseEntity<Void> deleteChallenge(@PathVariable String id) {
        log.debug("REST request to delete Challenge : {}", id);
        challengeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
