/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AdminTestModule } from '../../../test.module';
import { GeneralTableComponent } from 'app/entities/general-table/general-table.component';
import { GeneralTableService } from 'app/entities/general-table/general-table.service';
import { GeneralTable } from 'app/shared/model/general-table.model';

describe('Component Tests', () => {
    describe('GeneralTable Management Component', () => {
        let comp: GeneralTableComponent;
        let fixture: ComponentFixture<GeneralTableComponent>;
        let service: GeneralTableService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [GeneralTableComponent],
                providers: []
            })
                .overrideTemplate(GeneralTableComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GeneralTableComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeneralTableService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new GeneralTable(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.generalTables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
