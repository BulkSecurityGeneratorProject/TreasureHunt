/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TreasureHuntTestModule } from '../../../test.module';
import { ChallengeMySuffixDetailComponent } from 'app/entities/challenge-my-suffix/challenge-my-suffix-detail.component';
import { ChallengeMySuffix } from 'app/shared/model/challenge-my-suffix.model';

describe('Component Tests', () => {
    describe('ChallengeMySuffix Management Detail Component', () => {
        let comp: ChallengeMySuffixDetailComponent;
        let fixture: ComponentFixture<ChallengeMySuffixDetailComponent>;
        const route = ({ data: of({ challenge: new ChallengeMySuffix('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TreasureHuntTestModule],
                declarations: [ChallengeMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ChallengeMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ChallengeMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.challenge).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
