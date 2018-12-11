/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { GeneralTableUpdateComponent } from 'app/entities/general-table/general-table-update.component';
import { GeneralTableService } from 'app/entities/general-table/general-table.service';
import { GeneralTable } from 'app/shared/model/general-table.model';

describe('Component Tests', () => {
    describe('GeneralTable Management Update Component', () => {
        let comp: GeneralTableUpdateComponent;
        let fixture: ComponentFixture<GeneralTableUpdateComponent>;
        let service: GeneralTableService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [GeneralTableUpdateComponent]
            })
                .overrideTemplate(GeneralTableUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GeneralTableUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeneralTableService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GeneralTable(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.generalTable = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new GeneralTable();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.generalTable = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
