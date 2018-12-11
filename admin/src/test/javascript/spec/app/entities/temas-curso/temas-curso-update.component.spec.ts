/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { TemasCursoUpdateComponent } from 'app/entities/temas-curso/temas-curso-update.component';
import { TemasCursoService } from 'app/entities/temas-curso/temas-curso.service';
import { TemasCurso } from 'app/shared/model/temas-curso.model';

describe('Component Tests', () => {
    describe('TemasCurso Management Update Component', () => {
        let comp: TemasCursoUpdateComponent;
        let fixture: ComponentFixture<TemasCursoUpdateComponent>;
        let service: TemasCursoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [TemasCursoUpdateComponent]
            })
                .overrideTemplate(TemasCursoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TemasCursoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TemasCursoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TemasCurso(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.temasCurso = entity;
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
                    const entity = new TemasCurso();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.temasCurso = entity;
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
