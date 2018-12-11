/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { PortadaDetailComponent } from 'app/entities/portada/portada-detail.component';
import { Portada } from 'app/shared/model/portada.model';

describe('Component Tests', () => {
    describe('Portada Management Detail Component', () => {
        let comp: PortadaDetailComponent;
        let fixture: ComponentFixture<PortadaDetailComponent>;
        const route = ({ data: of({ portada: new Portada(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [PortadaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PortadaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PortadaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.portada).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
