import { IChallengeMySuffix } from 'app/shared/model//challenge-my-suffix.model';

export const enum LocationType {
    INSIDE = 'INSIDE',
    OUTSIDE = 'OUTSIDE'
}

export interface ILocationMySuffix {
    id?: string;
    type?: LocationType;
    location?: string;
    challenges?: IChallengeMySuffix[];
}

export class LocationMySuffix implements ILocationMySuffix {
    constructor(public id?: string, public type?: LocationType, public location?: string, public challenges?: IChallengeMySuffix[]) {}
}
