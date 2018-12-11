/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { HangoutUpdateComponent } from 'app/entities/hangout/hangout-update.component';
import { HangoutService } from 'app/entities/hangout/hangout.service';
import { Hangout } from 'app/shared/model/hangout.model';

describe('Component Tests', () => {
    describe('Hangout Management Update Component', () => {
        let comp: HangoutUpdateComponent;
        let fixture: ComponentFixture<HangoutUpdateComponent>;
        let service: HangoutService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [HangoutUpdateComponent]
            })
                .overrideTemplate(HangoutUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HangoutUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HangoutService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Hangout(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.hangout = entity;
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
                    const entity = new Hangout();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.hangout = entity;
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
