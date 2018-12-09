package com.jfn.treasure.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.jfn.treasure.domain.enumeration.ChallengeType;
import com.jfn.treasure.domain.enumeration.DifficultyType;

/**
 * A DTO for the Challenge entity.
 */
public class ChallengeDTO implements Serializable {

    private String id;

    @NotNull
    private String challenge;

    @NotNull
    private String solution;

    @NotNull
    private ChallengeType type;

    @NotNull
    private DifficultyType difficulty;

    private String locationId;

    private String huntId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public ChallengeType getType() {
        return type;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public DifficultyType getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyType difficulty) {
        this.difficulty = difficulty;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getHuntId() {
        return huntId;
    }

    public void setHuntId(String huntId) {
        this.huntId = huntId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChallengeDTO challengeDTO = (ChallengeDTO) o;
        if (challengeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), challengeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChallengeDTO{" +
            "id=" + getId() +
            ", challenge='" + getChallenge() + "'" +
            ", solution='" + getSolution() + "'" +
            ", type='" + getType() + "'" +
            ", difficulty='" + getDifficulty() + "'" +
            ", location=" + getLocationId() +
            ", hunt=" + getHuntId() +
            "}";
    }
}
