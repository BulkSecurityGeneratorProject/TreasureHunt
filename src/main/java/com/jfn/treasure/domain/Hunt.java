package com.jfn.treasure.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Hunt.
 */
@Document(collection = "hunt")
public class Hunt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

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

    public String getName() {
        return name;
    }

    public Hunt name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Challenge> getChallenges() {
        return challenges;
    }

    public Hunt challenges(Set<Challenge> challenges) {
        this.challenges = challenges;
        return this;
    }

    public Hunt addChallenge(Challenge challenge) {
        this.challenges.add(challenge);
        challenge.setHunt(this);
        return this;
    }

    public Hunt removeChallenge(Challenge challenge) {
        this.challenges.remove(challenge);
        challenge.setHunt(null);
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
        Hunt hunt = (Hunt) o;
        if (hunt.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hunt.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hunt{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
