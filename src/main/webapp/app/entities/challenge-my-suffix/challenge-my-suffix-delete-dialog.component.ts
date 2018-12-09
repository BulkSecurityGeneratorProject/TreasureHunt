import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';
import { ChallengeMySuffixService } from './challenge-my-suffix.service';

@Component({
    selector: 'jhi-challenge-my-suffix-delete-dialog',
    templateUrl: './challenge-my-suffix-delete-dialog.component.html'
})
export class ChallengeMySuffixDeleteDialogComponent {
    challenge: IChallengeMySuffix;

    constructor(
        private challengeService: ChallengeMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.challengeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'challengeListModification',
                content: 'Deleted an challenge'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-challenge-my-suffix-delete-popup',
    template: ''
})
export class ChallengeMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ challenge }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ChallengeMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.challenge = challenge;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
