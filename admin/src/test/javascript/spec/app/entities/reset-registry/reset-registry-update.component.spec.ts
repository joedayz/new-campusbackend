/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AdminTestModule } from '../../../test.module';
import { ResetRegistryUpdateComponent } from 'app/entities/reset-registry/reset-registry-update.component';
import { ResetRegistryService } from 'app/entities/reset-registry/reset-registry.service';
import { ResetRegistry } from 'app/shared/model/reset-registry.model';

describe('Component Tests', () => {
    describe('ResetRegistry Management Update Component', () => {
        let comp: ResetRegistryUpdateComponent;
        let fixture: ComponentFixture<ResetRegistryUpdateComponent>;
        let service: ResetRegistryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [ResetRegistryUpdateComponent]
            })
                .overrideTemplate(ResetRegistryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ResetRegistryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResetRegistryService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ResetRegistry(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.resetRegistry = entity;
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
                    const entity = new ResetRegistry();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.resetRegistry = entity;
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
