/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AdminTestModule } from '../../../test.module';
import { PortadaComponent } from 'app/entities/portada/portada.component';
import { PortadaService } from 'app/entities/portada/portada.service';
import { Portada } from 'app/shared/model/portada.model';

describe('Component Tests', () => {
    describe('Portada Management Component', () => {
        let comp: PortadaComponent;
        let fixture: ComponentFixture<PortadaComponent>;
        let service: PortadaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [PortadaComponent],
                providers: []
            })
                .overrideTemplate(PortadaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PortadaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PortadaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Portada(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.portadas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
