package com.jfn.treasure.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.jfn.treasure.domain.enumeration.ChallengeType;

import com.jfn.treasure.domain.enumeration.DifficultyType;

/**
 * A Challenge.
 */
@Document(collection = "challenge")
public class Challenge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("challenge")
    private String challenge;

    @NotNull
    @Field("solution")
    private String solution;

    @NotNull
    @Field("type")
    private ChallengeType type;

    @NotNull
    @Field("difficulty")
    private DifficultyType difficulty;

    @DBRef
    @Field("location")
    @JsonIgnoreProperties("challenges")
    private Location location;

    @DBRef
    @Field("hunt")
    @JsonIgnoreProperties("challenges")
    private Hunt hunt;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChallenge() {
        return challenge;
    }

    public Challenge challenge(String challenge) {
        this.challenge = challenge;
        return this;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getSolution() {
        return solution;
    }

    public Challenge solution(String solution) {
        this.solution = solution;
        return this;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public ChallengeType getType() {
        return type;
    }

    public Challenge type(ChallengeType type) {
        this.type = type;
        return this;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public DifficultyType getDifficulty() {
        return difficulty;
    }

    public Challenge difficulty(DifficultyType difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public void setDifficulty(DifficultyType difficulty) {
        this.difficulty = difficulty;
    }

    public Location getLocation() {
        return location;
    }

    public Challenge location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Hunt getHunt() {
        return hunt;
    }

    public Challenge hunt(Hunt hunt) {
        this.hunt = hunt;
        return this;
    }

    public void setHunt(Hunt hunt) {
        this.hunt = hunt;
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
        Challenge challenge = (Challenge) o;
        if (challenge.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), challenge.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Challenge{" +
            "id=" + getId() +
            ", challenge='" + getChallenge() + "'" +
            ", solution='" + getSolution() + "'" +
            ", type='" + getType() + "'" +
            ", difficulty='" + getDifficulty() + "'" +
            "}";
    }
}
