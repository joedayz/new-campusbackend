/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { HangoutDetailComponent } from 'app/entities/hangout/hangout-detail.component';
import { Hangout } from 'app/shared/model/hangout.model';

describe('Component Tests', () => {
    describe('Hangout Management Detail Component', () => {
        let comp: HangoutDetailComponent;
        let fixture: ComponentFixture<HangoutDetailComponent>;
        const route = ({ data: of({ hangout: new Hangout(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [HangoutDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HangoutDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HangoutDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.hangout).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
