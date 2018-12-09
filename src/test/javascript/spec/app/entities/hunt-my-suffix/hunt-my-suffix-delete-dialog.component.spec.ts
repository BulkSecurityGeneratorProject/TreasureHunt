/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { TreasureHuntTestModule } from '../../../test.module';
import { HuntMySuffixDeleteDialogComponent } from 'app/entities/hunt-my-suffix/hunt-my-suffix-delete-dialog.component';
import { HuntMySuffixService } from 'app/entities/hunt-my-suffix/hunt-my-suffix.service';

describe('Component Tests', () => {
    describe('HuntMySuffix Management Delete Component', () => {
        let comp: HuntMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<HuntMySuffixDeleteDialogComponent>;
        let service: HuntMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TreasureHuntTestModule],
                declarations: [HuntMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(HuntMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HuntMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HuntMySuffixService);
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
