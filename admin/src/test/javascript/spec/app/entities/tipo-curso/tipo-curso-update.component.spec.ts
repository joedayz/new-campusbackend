/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { TipoCursoUpdateComponent } from 'app/entities/tipo-curso/tipo-curso-update.component';
import { TipoCursoService } from 'app/entities/tipo-curso/tipo-curso.service';
import { TipoCurso } from 'app/shared/model/tipo-curso.model';

describe('Component Tests', () => {
    describe('TipoCurso Management Update Component', () => {
        let comp: TipoCursoUpdateComponent;
        let fixture: ComponentFixture<TipoCursoUpdateComponent>;
        let service: TipoCursoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [TipoCursoUpdateComponent]
            })
                .overrideTemplate(TipoCursoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoCursoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoCursoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TipoCurso(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tipoCurso = entity;
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
                    const entity = new TipoCurso();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tipoCurso = entity;
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
