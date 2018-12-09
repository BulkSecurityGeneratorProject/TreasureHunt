import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TreasureHuntSharedModule } from 'app/shared';
import {
    ChallengeMySuffixComponent,
    ChallengeMySuffixDetailComponent,
    ChallengeMySuffixUpdateComponent,
    ChallengeMySuffixDeletePopupComponent,
    ChallengeMySuffixDeleteDialogComponent,
    challengeRoute,
    challengePopupRoute
} from './';

const ENTITY_STATES = [...challengeRoute, ...challengePopupRoute];

@NgModule({
    imports: [TreasureHuntSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ChallengeMySuffixComponent,
        ChallengeMySuffixDetailComponent,
        ChallengeMySuffixUpdateComponent,
        ChallengeMySuffixDeleteDialogComponent,
        ChallengeMySuffixDeletePopupComponent
    ],
    entryComponents: [
        ChallengeMySuffixComponent,
        ChallengeMySuffixUpdateComponent,
        ChallengeMySuffixDeleteDialogComponent,
        ChallengeMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TreasureHuntChallengeMySuffixModule {}
