/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TreasureHuntTestModule } from '../../../test.module';
import { ChallengeMySuffixDeleteDialogComponent } from 'app/entities/challenge-my-suffix/challenge-my-suffix-delete-dialog.component';
import { ChallengeMySuffixService } from 'app/entities/challenge-my-suffix/challenge-my-suffix.service';

describe('Component Tests', () => {
    describe('ChallengeMySuffix Management Delete Component', () => {
        let comp: ChallengeMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<ChallengeMySuffixDeleteDialogComponent>;
        let service: ChallengeMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TreasureHuntTestModule],
                declarations: [ChallengeMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(ChallengeMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChallengeMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChallengeMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
