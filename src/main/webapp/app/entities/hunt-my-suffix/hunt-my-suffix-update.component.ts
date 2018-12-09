import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IHuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';
import { HuntMySuffixService } from './hunt-my-suffix.service';

@Component({
    selector: 'jhi-hunt-my-suffix-update',
    templateUrl: './hunt-my-suffix-update.component.html'
})
export class HuntMySuffixUpdateComponent implements OnInit {
    hunt: IHuntMySuffix;
    isSaving: boolean;

    constructor(private huntService: HuntMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hunt }) => {
            this.hunt = hunt;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.hunt.id !== undefined) {
            this.subscribeToSaveResponse(this.huntService.update(this.hunt));
        } else {
            this.subscribeToSaveResponse(this.huntService.create(this.hunt));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHuntMySuffix>>) {
        result.subscribe((res: HttpResponse<IHuntMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
