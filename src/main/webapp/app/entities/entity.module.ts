import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { TreasureHuntChallengeMySuffixModule } from './challenge-my-suffix/challenge-my-suffix.module';
import { TreasureHuntLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { TreasureHuntHuntMySuffixModule } from './hunt-my-suffix/hunt-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        TreasureHuntChallengeMySuffixModule,
        TreasureHuntLocationMySuffixModule,
        TreasureHuntHuntMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TreasureHuntEntityModule {}
