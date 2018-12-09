package com.jfn.treasure.service;

import com.jfn.treasure.service.dto.HuntDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Hunt.
 */
public interface HuntService {

    /**
     * Save a hunt.
     *
     * @param huntDTO the entity to save
     * @return the persisted entity
     */
    HuntDTO save(HuntDTO huntDTO);

    /**
     * Get all the hunts.
     *
     * @return the list of entities
     */
    List<HuntDTO> findAll();


    /**
     * Get the "id" hunt.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HuntDTO> findOne(String id);

    /**
     * Delete the "id" hunt.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
