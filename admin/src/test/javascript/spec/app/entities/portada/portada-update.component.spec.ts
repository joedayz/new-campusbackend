/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { PortadaUpdateComponent } from 'app/entities/portada/portada-update.component';
import { PortadaService } from 'app/entities/portada/portada.service';
import { Portada } from 'app/shared/model/portada.model';

describe('Component Tests', () => {
    describe('Portada Management Update Component', () => {
        let comp: PortadaUpdateComponent;
        let fixture: ComponentFixture<PortadaUpdateComponent>;
        let service: PortadaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [PortadaUpdateComponent]
            })
                .overrideTemplate(PortadaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PortadaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PortadaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Portada(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.portada = entity;
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
                    const entity = new Portada();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.portada = entity;
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
