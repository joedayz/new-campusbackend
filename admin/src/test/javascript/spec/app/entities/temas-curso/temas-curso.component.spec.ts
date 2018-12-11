/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AdminTestModule } from '../../../test.module';
import { TemasCursoComponent } from 'app/entities/temas-curso/temas-curso.component';
import { TemasCursoService } from 'app/entities/temas-curso/temas-curso.service';
import { TemasCurso } from 'app/shared/model/temas-curso.model';

describe('Component Tests', () => {
    describe('TemasCurso Management Component', () => {
        let comp: TemasCursoComponent;
        let fixture: ComponentFixture<TemasCursoComponent>;
        let service: TemasCursoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [TemasCursoComponent],
                providers: []
            })
                .overrideTemplate(TemasCursoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TemasCursoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TemasCursoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TemasCurso(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.temasCursos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
