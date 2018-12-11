/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AdminTestModule } from '../../../test.module';
import { GeneralTableDeleteDialogComponent } from 'app/entities/general-table/general-table-delete-dialog.component';
import { GeneralTableService } from 'app/entities/general-table/general-table.service';

describe('Component Tests', () => {
    describe('GeneralTable Management Delete Component', () => {
        let comp: GeneralTableDeleteDialogComponent;
        let fixture: ComponentFixture<GeneralTableDeleteDialogComponent>;
        let service: GeneralTableService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AdminTestModule],
                declarations: [GeneralTableDeleteDialogComponent]
            })
                .overrideTemplate(GeneralTableDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GeneralTableDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeneralTableService);
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
