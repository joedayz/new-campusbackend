/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { DatosCursoDetailComponent } from 'app/entities/datos-curso/datos-curso-detail.component';
import { DatosCurso } from 'app/shared/model/datos-curso.model';

describe('Component Tests', () => {
    describe('DatosCurso Management Detail Component', () => {
        let comp: DatosCursoDetailComponent;
        let fixture: ComponentFixture<DatosCursoDetailComponent>;
        const route = ({ data: of({ datosCurso: new DatosCurso(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [DatosCursoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DatosCursoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DatosCursoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.datosCurso).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
