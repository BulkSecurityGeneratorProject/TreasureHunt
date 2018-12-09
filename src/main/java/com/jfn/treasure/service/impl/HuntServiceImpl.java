package com.jfn.treasure.service.impl;

import com.jfn.treasure.service.HuntService;
import com.jfn.treasure.domain.Hunt;
import com.jfn.treasure.repository.HuntRepository;
import com.jfn.treasure.service.dto.HuntDTO;
import com.jfn.treasure.service.mapper.HuntMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Hunt.
 */
@Service
public class HuntServiceImpl implements HuntService {

    private final Logger log = LoggerFactory.getLogger(HuntServiceImpl.class);

    private final HuntRepository huntRepository;

    private final HuntMapper huntMapper;

    public HuntServiceImpl(HuntRepository huntRepository, HuntMapper huntMapper) {
        this.huntRepository = huntRepository;
        this.huntMapper = huntMapper;
    }

    /**
     * Save a hunt.
     *
     * @param huntDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HuntDTO save(HuntDTO huntDTO) {
        log.debug("Request to save Hunt : {}", huntDTO);

        Hunt hunt = huntMapper.toEntity(huntDTO);
        hunt = huntRepository.save(hunt);
        return huntMapper.toDto(hunt);
    }

    /**
     * Get all the hunts.
     *
     * @return the list of entities
     */
    @Override
    public List<HuntDTO> findAll() {
        log.debug("Request to get all Hunts");
        return huntRepository.findAll().stream()
            .map(huntMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one hunt by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<HuntDTO> findOne(String id) {
        log.debug("Request to get Hunt : {}", id);
        return huntRepository.findById(id)
            .map(huntMapper::toDto);
    }

    /**
     * Delete the hunt by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Hunt : {}", id);
        huntRepository.deleteById(id);
    }
}
