package com.jfn.treasure.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.jfn.treasure.domain.enumeration.LocationType;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Document(collection = "location")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("type")
    private LocationType type;

    @NotNull
    @Field("location")
    private String location;

    @DBRef
    @Field("challenge")
    private Set<Challenge> challenges = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocationType getType() {
        return type;
    }

    public Location type(LocationType type) {
        this.type = type;
        return this;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public Location location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Challenge> getChallenges() {
        return challenges;
    }

    public Location challenges(Set<Challenge> challenges) {
        this.challenges = challenges;
        return this;
    }

    public Location addChallenge(Challenge challenge) {
        this.challenges.add(challenge);
        challenge.setLocation(this);
        return this;
    }

    public Location removeChallenge(Challenge challenge) {
        this.challenges.remove(challenge);
        challenge.setLocation(null);
        return this;
    }

    public void setChallenges(Set<Challenge> challenges) {
        this.challenges = challenges;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        if (location.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), location.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Location{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}
