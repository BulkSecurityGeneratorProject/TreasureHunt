import { NgModule } from '@angular/core';

import { TreasureHuntSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [TreasureHuntSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [TreasureHuntSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TreasureHuntSharedCommonModule {}
