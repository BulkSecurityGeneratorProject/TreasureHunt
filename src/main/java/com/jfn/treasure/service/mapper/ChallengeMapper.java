package com.jfn.treasure.service.mapper;

import com.jfn.treasure.domain.*;
import com.jfn.treasure.service.dto.ChallengeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Challenge and its DTO ChallengeDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, HuntMapper.class})
public interface ChallengeMapper extends EntityMapper<ChallengeDTO, Challenge> {

    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "hunt.id", target = "huntId")
    ChallengeDTO toDto(Challenge challenge);

    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "huntId", target = "hunt")
    Challenge toEntity(ChallengeDTO challengeDTO);

    default Challenge fromId(String id) {
        if (id == null) {
            return null;
        }
        Challenge challenge = new Challenge();
        challenge.setId(id);
        return challenge;
    }
}
