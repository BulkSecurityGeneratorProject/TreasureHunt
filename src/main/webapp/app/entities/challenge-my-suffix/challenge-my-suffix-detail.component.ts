import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';

@Component({
    selector: 'jhi-challenge-my-suffix-detail',
    templateUrl: './challenge-my-suffix-detail.component.html'
})
export class ChallengeMySuffixDetailComponent implements OnInit {
    challenge: IChallengeMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ challenge }) => {
            this.challenge = challenge;
        });
    }

    previousState() {
        window.history.back();
    }
}
