/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TreasureHuntTestModule } from '../../../test.module';
import { HuntMySuffixComponent } from 'app/entities/hunt-my-suffix/hunt-my-suffix.component';
import { HuntMySuffixService } from 'app/entities/hunt-my-suffix/hunt-my-suffix.service';
import { HuntMySuffix } from 'app/shared/model/hunt-my-suffix.model';

describe('Component Tests', () => {
    describe('HuntMySuffix Management Component', () => {
        let comp: HuntMySuffixComponent;
        let fixture: ComponentFixture<HuntMySuffixComponent>;
        let service: HuntMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [TreasureHuntTestModule],
                declarations: [HuntMySuffixComponent],
                providers: []
            })
                .overrideTemplate(HuntMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HuntMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HuntMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new HuntMySuffix('123')],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.hunts[0]).toEqual(jasmine.objectContaining({ id: '123' }));
        });
    });
});
