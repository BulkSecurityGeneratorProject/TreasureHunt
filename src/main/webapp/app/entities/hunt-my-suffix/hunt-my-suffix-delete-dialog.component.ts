import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';
import { HuntMySuffixService } from './hunt-my-suffix.service';

@Component({
    selector: 'jhi-hunt-my-suffix-delete-dialog',
    templateUrl: './hunt-my-suffix-delete-dialog.component.html'
})
export class HuntMySuffixDeleteDialogComponent {
    hunt: IHuntMySuffix;

    constructor(private huntService: HuntMySuffixService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.huntService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'huntListModification',
                content: 'Deleted an hunt'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-hunt-my-suffix-delete-popup',
    template: ''
})
export class HuntMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hunt }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HuntMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.hunt = hunt;
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
