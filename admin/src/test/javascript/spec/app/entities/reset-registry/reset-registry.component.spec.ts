/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AdminTestModule } from '../../../test.module';
import { ResetRegistryComponent } from 'app/entities/reset-registry/reset-registry.component';
import { ResetRegistryService } from 'app/entities/reset-registry/reset-registry.service';
import { ResetRegistry } from 'app/shared/model/reset-registry.model';

describe('Component Tests', () => {
    describe('ResetRegistry Management Component', () => {
        let comp: ResetRegistryComponent;
        let fixture: ComponentFixture<ResetRegistryComponent>;
        let service: ResetRegistryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [ResetRegistryComponent],
                providers: []
            })
                .overrideTemplate(ResetRegistryComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ResetRegistryComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResetRegistryService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ResetRegistry(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.resetRegistries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
