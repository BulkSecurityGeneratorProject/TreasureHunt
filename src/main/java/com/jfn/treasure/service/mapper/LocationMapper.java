package com.jfn.treasure.service.mapper;

import com.jfn.treasure.domain.*;
import com.jfn.treasure.service.dto.LocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Location and its DTO LocationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {


    @Mapping(target = "challenges", ignore = true)
    Location toEntity(LocationDTO locationDTO);

    default Location fromId(String id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
