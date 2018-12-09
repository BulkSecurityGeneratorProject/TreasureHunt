import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';

@Component({
    selector: 'jhi-hunt-my-suffix-detail',
    templateUrl: './hunt-my-suffix-detail.component.html'
})
export class HuntMySuffixDetailComponent implements OnInit {
    hunt: IHuntMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hunt }) => {
            this.hunt = hunt;
        });
    }

    previousState() {
        window.history.back();
    }
}
