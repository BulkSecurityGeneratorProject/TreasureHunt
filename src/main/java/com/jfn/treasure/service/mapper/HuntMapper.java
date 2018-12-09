package com.jfn.treasure.service.mapper;

import com.jfn.treasure.domain.*;
import com.jfn.treasure.service.dto.HuntDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Hunt and its DTO HuntDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HuntMapper extends EntityMapper<HuntDTO, Hunt> {


    @Mapping(target = "challenges", ignore = true)
    Hunt toEntity(HuntDTO huntDTO);

    default Hunt fromId(String id) {
        if (id == null) {
            return null;
        }
        Hunt hunt = new Hunt();
        hunt.setId(id);
        return hunt;
    }
}
