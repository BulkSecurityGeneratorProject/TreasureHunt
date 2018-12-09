import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IHuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';
import { Principal } from 'app/core';
import { HuntMySuffixService } from './hunt-my-suffix.service';

@Component({
    selector: 'jhi-hunt-my-suffix',
    templateUrl: './hunt-my-suffix.component.html'
})
export class HuntMySuffixComponent implements OnInit, OnDestroy {
    hunts: IHuntMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private huntService: HuntMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.huntService.query().subscribe(
            (res: HttpResponse<IHuntMySuffix[]>) => {
                this.hunts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInHunts();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IHuntMySuffix) {
        return item.id;
    }

    registerChangeInHunts() {
        this.eventSubscriber = this.eventManager.subscribe('huntListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
