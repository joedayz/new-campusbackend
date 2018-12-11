/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AdminTestModule } from '../../../test.module';
import { TipoCursoComponent } from 'app/entities/tipo-curso/tipo-curso.component';
import { TipoCursoService } from 'app/entities/tipo-curso/tipo-curso.service';
import { TipoCurso } from 'app/shared/model/tipo-curso.model';

describe('Component Tests', () => {
    describe('TipoCurso Management Component', () => {
        let comp: TipoCursoComponent;
        let fixture: ComponentFixture<TipoCursoComponent>;
        let service: TipoCursoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [TipoCursoComponent],
                providers: []
            })
                .overrideTemplate(TipoCursoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoCursoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoCursoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TipoCurso(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tipoCursos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
