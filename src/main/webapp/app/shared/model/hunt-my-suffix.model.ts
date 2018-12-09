import { IChallengeMySuffix } from 'app/shared/model//challenge-my-suffix.model';

export interface IHuntMySuffix {
    id?: string;
    name?: string;
    challenges?: IChallengeMySuffix[];
}

export class HuntMySuffix implements IHuntMySuffix {
    constructor(public id?: string, public name?: string, public challenges?: IChallengeMySuffix[]) {}
}
