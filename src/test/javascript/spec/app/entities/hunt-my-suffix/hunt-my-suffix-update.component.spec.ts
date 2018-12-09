/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { TreasureHuntTestModule } from '../../../test.module';
import { HuntMySuffixUpdateComponent } from 'app/entities/hunt-my-suffix/hunt-my-suffix-update.component';
import { HuntMySuffixService } from 'app/entities/hunt-my-suffix/hunt-my-suffix.service';
import { HuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';

describe('Component Tests', () => {
    describe('HuntMySuffix Management Update Component', () => {
        let comp: HuntMySuffixUpdateComponent;
        let fixture: ComponentFixture<HuntMySuffixUpdateComponent>;
        let service: HuntMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TreasureHuntTestModule],
                declarations: [HuntMySuffixUpdateComponent]
            })
                .overrideTemplate(HuntMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HuntMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HuntMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new HuntMySuffix('123');
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.hunt = entity;
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
                    const entity = new HuntMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.hunt = entity;
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
