/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AdminTestModule } from '../../../test.module';
import { ResetRegistryDeleteDialogComponent } from 'app/entities/reset-registry/reset-registry-delete-dialog.component';
import { ResetRegistryService } from 'app/entities/reset-registry/reset-registry.service';

describe('Component Tests', () => {
    describe('ResetRegistry Management Delete Component', () => {
        let comp: ResetRegistryDeleteDialogComponent;
        let fixture: ComponentFixture<ResetRegistryDeleteDialogComponent>;
        let service: ResetRegistryService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [ResetRegistryDeleteDialogComponent]
            })
                .overrideTemplate(ResetRegistryDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ResetRegistryDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResetRegistryService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
