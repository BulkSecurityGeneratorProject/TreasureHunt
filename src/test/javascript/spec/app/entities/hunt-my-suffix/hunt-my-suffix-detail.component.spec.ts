/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TreasureHuntTestModule } from '../../../test.module';
import { HuntMySuffixDetailComponent } from 'app/entities/hunt-my-suffix/hunt-my-suffix-detail.component';
import { HuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';

describe('Component Tests', () => {
    describe('HuntMySuffix Management Detail Component', () => {
        let comp: HuntMySuffixDetailComponent;
        let fixture: ComponentFixture<HuntMySuffixDetailComponent>;
        const route = ({ data: of({ hunt: new HuntMySuffix('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TreasureHuntTestModule],
                declarations: [HuntMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HuntMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HuntMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.hunt).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
