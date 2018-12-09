import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';
import { ChallengeMySuffixService } from './challenge-my-suffix.service';
import { ILocationMySuffix } from 'app/shared/model/location-my-suffix.model';
import { LocationMySuffixService } from 'app/entities/location-my-suffix';
import { IHuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';
import { HuntMySuffixService } from 'app/entities/hunt-my-suffix';

@Component({
    selector: 'jhi-challenge-my-suffix-update',
    templateUrl: './challenge-my-suffix-update.component.html'
})
export class ChallengeMySuffixUpdateComponent implements OnInit {
    challenge: IChallengeMySuffix;
    isSaving: boolean;

    locations: ILocationMySuffix[];

    hunts: IHuntMySuffix[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private challengeService: ChallengeMySuffixService,
        private locationService: LocationMySuffixService,
        private huntService: HuntMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ challenge }) => {
            this.challenge = challenge;
        });
        this.locationService.query().subscribe(
            (res: HttpResponse<ILocationMySuffix[]>) => {
                this.locations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.huntService.query().subscribe(
            (res: HttpResponse<IHuntMySuffix[]>) => {
                this.hunts = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.challenge.id !== undefined) {
            this.subscribeToSaveResponse(this.challengeService.update(this.challenge));
        } else {
            this.subscribeToSaveResponse(this.challengeService.create(this.challenge));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IChallengeMySuffix>>) {
        result.subscribe((res: HttpResponse<IChallengeMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackLocationById(index: number, item: ILocationMySuffix) {
        return item.id;
    }

    trackHuntById(index: number, item: IHuntMySuffix) {
        return item.id;
    }
}
