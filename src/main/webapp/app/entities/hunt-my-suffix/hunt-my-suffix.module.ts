import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TreasureHuntSharedModule } from 'app/shared';
import {
    HuntMySuffixComponent,
    HuntMySuffixDetailComponent,
    HuntMySuffixUpdateComponent,
    HuntMySuffixDeletePopupComponent,
    HuntMySuffixDeleteDialogComponent,
    huntRoute,
    huntPopupRoute
} from './';

const ENTITY_STATES = [...huntRoute, ...huntPopupRoute];

@NgModule({
    imports: [TreasureHuntSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HuntMySuffixComponent,
        HuntMySuffixDetailComponent,
        HuntMySuffixUpdateComponent,
        HuntMySuffixDeleteDialogComponent,
        HuntMySuffixDeletePopupComponent
    ],
    entryComponents: [
        HuntMySuffixComponent,
        HuntMySuffixUpdateComponent,
        HuntMySuffixDeleteDialogComponent,
        HuntMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class TreasureHuntHuntMySuffixModule {}
