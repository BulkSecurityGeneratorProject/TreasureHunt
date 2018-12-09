/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { TreasureHuntTestModule } from '../../../test.module';
import { ChallengeMySuffixUpdateComponent } from 'app/entities/challenge-my-suffix/challenge-my-suffix-update.component';
import { ChallengeMySuffixService } from 'app/entities/challenge-my-suffix/challenge-my-suffix.service';
import { ChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';

describe('Component Tests', () => {
    describe('ChallengeMySuffix Management Update Component', () => {
        let comp: ChallengeMySuffixUpdateComponent;
        let fixture: ComponentFixture<ChallengeMySuffixUpdateComponent>;
        let service: ChallengeMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TreasureHuntTestModule],
                declarations: [ChallengeMySuffixUpdateComponent]
            })
                .overrideTemplate(ChallengeMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ChallengeMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ChallengeMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ChallengeMySuffix('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.challenge = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ChallengeMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.challenge = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
