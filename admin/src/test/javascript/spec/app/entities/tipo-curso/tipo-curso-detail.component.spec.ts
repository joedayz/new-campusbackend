/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { TipoCursoDetailComponent } from 'app/entities/tipo-curso/tipo-curso-detail.component';
import { TipoCurso } from 'app/shared/model/tipo-curso.model';

describe('Component Tests', () => {
    describe('TipoCurso Management Detail Component', () => {
        let comp: TipoCursoDetailComponent;
        let fixture: ComponentFixture<TipoCursoDetailComponent>;
        const route = ({ data: of({ tipoCurso: new TipoCurso(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [TipoCursoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TipoCursoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TipoCursoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tipoCurso).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
