package com.jfn.treasure.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Hunt entity.
 */
public class HuntDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HuntDTO huntDTO = (HuntDTO) o;
        if (huntDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), huntDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HuntDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
