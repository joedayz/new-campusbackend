/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AdminTestModule } from '../../../test.module';
import { DatosCursoComponent } from 'app/entities/datos-curso/datos-curso.component';
import { DatosCursoService } from 'app/entities/datos-curso/datos-curso.service';
import { DatosCurso } from 'app/shared/model/datos-curso.model';

describe('Component Tests', () => {
    describe('DatosCurso Management Component', () => {
        let comp: DatosCursoComponent;
        let fixture: ComponentFixture<DatosCursoComponent>;
        let service: DatosCursoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [DatosCursoComponent],
                providers: []
            })
                .overrideTemplate(DatosCursoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DatosCursoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DatosCursoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new DatosCurso(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.datosCursos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
