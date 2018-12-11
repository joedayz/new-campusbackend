/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { TemasCursoDetailComponent } from 'app/entities/temas-curso/temas-curso-detail.component';
import { TemasCurso } from 'app/shared/model/temas-curso.model';

describe('Component Tests', () => {
    describe('TemasCurso Management Detail Component', () => {
        let comp: TemasCursoDetailComponent;
        let fixture: ComponentFixture<TemasCursoDetailComponent>;
        const route = ({ data: of({ temasCurso: new TemasCurso(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [TemasCursoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TemasCursoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TemasCursoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.temasCurso).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
