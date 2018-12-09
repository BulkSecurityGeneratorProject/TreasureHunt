export const enum ChallengeType {
    RIDDLE = 'RIDDLE'
}

export const enum DifficultyType {
    EASY = 'EASY',
    COMPLEX = 'COMPLEX'
}

export interface IChallengeMySuffix {
    id?: string;
    challenge?: string;
    solution?: string;
    type?: ChallengeType;
    difficulty?: DifficultyType;
    locationId?: string;
    huntId?: string;
}

export class ChallengeMySuffix implements IChallengeMySuffix {
    constructor(
        public id?: string,
        public challenge?: string,
        public solution?: string,
        public type?: ChallengeType,
        public difficulty?: DifficultyType,
        public locationId?: string,
        public huntId?: string
    ) {}
}
