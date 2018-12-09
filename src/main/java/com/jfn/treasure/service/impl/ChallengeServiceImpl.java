package com.jfn.treasure.service.impl;

import com.jfn.treasure.service.ChallengeService;
import com.jfn.treasure.domain.Challenge;
import com.jfn.treasure.repository.ChallengeRepository;
import com.jfn.treasure.service.dto.ChallengeDTO;
import com.jfn.treasure.service.mapper.ChallengeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing Challenge.
 */
@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final Logger log = LoggerFactory.getLogger(ChallengeServiceImpl.class);

    private final ChallengeRepository challengeRepository;

    private final ChallengeMapper challengeMapper;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository, ChallengeMapper challengeMapper) {
        this.challengeRepository = challengeRepository;
        this.challengeMapper = challengeMapper;
    }

    /**
     * Save a challenge.
     *
     * @param challengeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ChallengeDTO save(ChallengeDTO challengeDTO) {
        log.debug("Request to save Challenge : {}", challengeDTO);

        Challenge challenge = challengeMapper.toEntity(challengeDTO);
        challenge = challengeRepository.save(challenge);
        return challengeMapper.toDto(challenge);
    }

    /**
     * Get all the challenges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<ChallengeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Challenges");
        return challengeRepository.findAll(pageable)
            .map(challengeMapper::toDto);
    }


    /**
     * Get one challenge by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<ChallengeDTO> findOne(String id) {
        log.debug("Request to get Challenge : {}", id);
        return challengeRepository.findById(id)
            .map(challengeMapper::toDto);
    }

    /**
     * Delete the challenge by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Challenge : {}", id);
        challengeRepository.deleteById(id);
    }
}
