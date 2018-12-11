/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { DatosCursoUpdateComponent } from 'app/entities/datos-curso/datos-curso-update.component';
import { DatosCursoService } from 'app/entities/datos-curso/datos-curso.service';
import { DatosCurso } from 'app/shared/model/datos-curso.model';

describe('Component Tests', () => {
    describe('DatosCurso Management Update Component', () => {
        let comp: DatosCursoUpdateComponent;
        let fixture: ComponentFixture<DatosCursoUpdateComponent>;
        let service: DatosCursoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [DatosCursoUpdateComponent]
            })
                .overrideTemplate(DatosCursoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DatosCursoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DatosCursoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DatosCurso(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.datosCurso = entity;
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
                    const entity = new DatosCurso();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.datosCurso = entity;
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
